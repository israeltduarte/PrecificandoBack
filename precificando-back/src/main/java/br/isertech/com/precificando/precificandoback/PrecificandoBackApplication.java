package br.isertech.com.precificando.precificandoback;

import br.isertech.com.precificando.precificandoback.entity.Role;
import br.isertech.com.precificando.precificandoback.enums.RoleType;
import br.isertech.com.precificando.precificandoback.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", maxAge = 3600)
public class PrecificandoBackApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(PrecificandoBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role user = Role.builder()
                .roleName(RoleType.ROLE_USER)
                .build();

        roleRepository.save(user);
    }
}
