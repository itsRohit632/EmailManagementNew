// src/main/java/com/projectmanagement/EmailManagementNewApplication.java
package com.projectmanagement;

import com.projectmanagement.model.Role;
import com.projectmanagement.model.Role.RoleName;
import com.projectmanagement.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EmailManagementNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailManagementNewApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
                var r = new Role(); r.setName(RoleName.ROLE_USER);
                roleRepository.save(r);
            }
            if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
                var r = new Role(); r.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(r);
            }
        };
    }
}
