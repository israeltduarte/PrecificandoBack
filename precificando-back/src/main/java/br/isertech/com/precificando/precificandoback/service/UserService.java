package br.isertech.com.precificando.precificandoback.service;

import br.isertech.com.precificando.precificandoback.constants.Messages;
import br.isertech.com.precificando.precificandoback.dto.UserDTO;
import br.isertech.com.precificando.precificandoback.entity.MIUser;
import br.isertech.com.precificando.precificandoback.entity.Role;
import br.isertech.com.precificando.precificandoback.error.exception.UserNotFoundException;
import br.isertech.com.precificando.precificandoback.repository.UserRepository;
import br.isertech.com.precificando.precificandoback.util.MIUserTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<MIUser> getAllUsers(Pageable pageable) {

        Page<MIUser> users = userRepository.findAll(pageable);
        log.info("UserService - getAllUsers() - Page<MIUser>={}", users);

        return users;
    }

    public MIUser addUser(UserDTO userDTO) {

        MIUser user = getNewUserEntityReady(userDTO);
        user = userRepository.save(user);
        log.info("UserService - addUser() - MIUser = {}", user);

        return user;
    }

    private MIUser getNewUserEntityReady(UserDTO userDTO) {

        LocalDateTime time = LocalDateTime.now();
        List<Role> roles = roleService.checkAndGetRoles(userDTO.getRoles());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        MIUser user = MIUserTransformer.fromDTO(userDTO);
        user.setCreated(time);
        user.setUpdated(time);
        user.setRoles(roles);

        return user;
    }

    public MIUser getUserById(String userId) {

        MIUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));
        log.info("UserService - getUserById() - UserDTO={}", user);

        return user;
    }

    public boolean existsByUsername(String username) {

        boolean exists = userRepository.findByUsername(username).isPresent();
        log.warn("UserService - existsByUsername() - " + Messages.USERNAME_ALREADY_EXISTS);

        return exists;
    }

    public boolean existsByEmail(String email) {

        boolean exists = userRepository.findByEmail(email).isPresent();
        log.warn("UserService - existsByEmail() - " + Messages.EMAIL_ALREADY_EXISTS);

        return exists;
    }

    public MIUser updateUserById(UserDTO userDTO, String userId) {

        MIUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));

        List<Role> roles;

        user = MIUserTransformer.fromDTO(user, userDTO);

        if (null != userDTO.getRoles() && !userDTO.getRoles().isEmpty()) {
            roles = roleService.checkAndGetRoles(userDTO.getRoles());
            user.setRoles(roles);
        }

        user = userRepository.save(user);
        log.info("UserService - updateUserById() - MIUser={}", user);

        return user;
    }

    public void deleteUserById(String userId) {

        MIUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND_INFO));

        userRepository.delete(user);

        log.info("UserService - deleteUserById() - ".concat(Messages.USER_DELETED));
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
        log.info("UserService - deleteAllUsers() - ".concat(Messages.USERS_DELETED));
    }

}
