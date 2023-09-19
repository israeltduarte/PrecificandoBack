package br.isertech.com.precificando.precificandoback.repository;

import br.isertech.com.precificando.precificandoback.entity.Role;
import br.isertech.com.precificando.precificandoback.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    Optional<Role> findByRoleName(RoleType roleType);

}
