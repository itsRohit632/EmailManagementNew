package com.projectmanagement.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectmanagement.dto.UserDTO;
import com.projectmanagement.model.Role;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.RoleRepository;
import com.projectmanagement.repo.UserRepository;
import com.projectmanagement.utility.EmailService;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;

    @Value("${admin.email}")
    private String adminEmail;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
    }

    public String register(UserDTO dto) {
        // ✅ Check if user already exists
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already registered.");
        }

        // ✅ Create User entity
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRegisteredAt(LocalDateTime.now());

        // ✅ Fetch ROLE_USER from DB
        Role roleUser = roleRepository.findByName(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found in the database!"));

        // ✅ Debug log for role
        System.out.println("Assigned role: ID=" + roleUser.getId() + ", Name=" + roleUser.getName());

        // ✅ Assign role to user
        user.getRoles().add(roleUser);

        // ✅ Save user to DB
        userRepository.save(user);

        // ✅ Send welcome email to the user
        emailService.sendEmail(
                user.getEmail(),
                "Welcome to Email Management!",
                "Hi " + user.getFullName() + ", your account has been successfully created!"
        );

        // ✅ Send notification email to admin
        emailService.sendEmail(
                adminEmail,
                "New User Registered",
                "🚨 A new user just registered:\n\n"
                        + "Name: " + user.getFullName() + "\n"
                        + "Email: " + user.getEmail()
        );

        return "Registration successful!";
    }
}
