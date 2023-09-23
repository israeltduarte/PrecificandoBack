package br.isertech.com.precificando.precificandoback.service;

import br.isertech.com.precificando.precificandoback.constants.Messages;
import br.isertech.com.precificando.precificandoback.dto.RoleDTO;
import br.isertech.com.precificando.precificandoback.entity.Role;
import br.isertech.com.precificando.precificandoback.enums.RoleType;
import br.isertech.com.precificando.precificandoback.error.exception.OperationFailedException;
import br.isertech.com.precificando.precificandoback.error.exception.RoleAlreadyExistsException;
import br.isertech.com.precificando.precificandoback.error.exception.RoleNotFoundException;
import br.isertech.com.precificando.precificandoback.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {

        List<Role> roles = roleRepository.findAll();
        log.info("RoleService - getAllRoles() - List<Role>={}", roles);

        return roles;
    }

    public Role registerRole(RoleDTO roleDTO) {

        log.info("RoleService - registerRole() - RoleDTO={}", roleDTO);
        Role role = Role.builder()
                .roleName(RoleType.valueOf(roleDTO.getRoleName()))
                .build();
        try {
            role = roleRepository.save(role);
        } catch (DataIntegrityViolationException e) {
            String message = Messages.ROLE_ALREADY_EXISTS.concat(". RoleType = " + roleDTO.getRoleName());
            log.error(message);
            throw new RoleAlreadyExistsException(message);
        } catch (Exception e) {
            log.error(Messages.OPERATION_FAILED);
            throw new OperationFailedException(Messages.OPERATION_FAILED);
        }
        log.info("RoleService - registerRole() - Role={}", role);

        return role;
    }

    public Role findByRoleName(String roleName) {

        RoleType roleType;
        try {
            roleType = RoleType.valueOf(roleName);
        } catch (Exception e) {
            throw new RoleNotFoundException(Messages.ROLE_NOT_FOUND_INFO.concat(roleName));
        }
        Role role = roleRepository.findByRoleName(roleType)
                .orElseThrow(() -> new RoleNotFoundException(Messages.ROLE_NOT_FOUND_INFO));
        log.info("RoleService - findByRoleName() - Role={}", role);

        return role;
    }

    public List<Role> checkAndGetRoles(List<String> rolesDTO) {

        return rolesDTO.parallelStream()
                .map(this::findByRoleName)
                .toList();
    }
}
