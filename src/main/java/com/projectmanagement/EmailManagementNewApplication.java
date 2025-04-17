package com.projectmanagement;

import com.projectmanagement.model.Role;
import com.projectmanagement.model.Role.RoleName;
import com.projectmanagement.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmailManagementNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmailManagementNewApplication.class, args);
    }

    // Role seeding on app start
    @Bean
    public CommandLineRunner seedRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName(RoleName.ROLE_USER).isEmpty()) {
                Role userRole = new Role();
                userRole.setName(RoleName.ROLE_USER);
                roleRepository.save(userRole);
                System.out.println("Seeded ROLE_USER");
            }

            if (roleRepository.findByName(RoleName.ROLE_ADMIN).isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(adminRole);
                System.out.println("Seeded ROLE_ADMIN");
            }
        };
    }
}
