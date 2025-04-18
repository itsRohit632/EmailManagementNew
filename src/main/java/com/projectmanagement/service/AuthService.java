package com.projectmanagement.service;

import com.projectmanagement.dto.UserDTO;
import com.projectmanagement.model.Role;
import com.projectmanagement.model.User;
import com.projectmanagement.repo.RoleRepository;
import com.projectmanagement.repo.UserRepository;
import com.projectmanagement.utility.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already registered.");
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // ✅ Use injected encoder
        user.setAddress(dto.getAddress());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRegisteredAt(LocalDateTime.now());

        Role roleUser = roleRepository.findByName(Role.RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found in the database!"));

        user.getRoles().add(roleUser);
        userRepository.save(user);

        emailService.sendEmail(
                user.getEmail(),
                "Welcome to Email Management!",
                "Hi " + user.getFullName() + ", your account has been successfully created!"
        );

        emailService.sendEmail(
                adminEmail,
                "New User Registered",
                "🚨 A new user just registered:\n\n"
                        + "Name: " + user.getFullName() + "\n"
                        + "Email: " + user.getEmail()
        );

        return "Registration successful!";
    }

    // ✅ NEW: For basic auth login testing
    public String login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return "User not found!";
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            return "Invalid credentials!";
        }

        return "Login successful ✅";
    }
}
