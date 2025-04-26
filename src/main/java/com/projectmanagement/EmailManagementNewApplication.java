package com.projectmanagement;

import com.projectmanagement.model.Role;
import com.projectmanagement.model.Role.RoleName;
import com.projectmanagement.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
// ← add this import
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// ← enable the scheduler
@EnableScheduling
public class EmailManagementNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailManagementNewApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
                Role userRole = new Role();
                userRole.setName(RoleName.ROLE_USER);
                roleRepository.save(userRole);
            }
            if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(adminRole);
            }
        };
    }
}
