package com.projectmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs (especially useful with Postman)
            .authorizeHttpRequests(auth -> auth
                // Public APIs
                .requestMatchers("/api/auth/**", "/api/user-details/**").permitAll()

                // Authenticated access required for materials and all stage endpoints
                .requestMatchers("/api/materials/**").authenticated()
                .requestMatchers(
                    "/api/stage2/**",
                    "/api/stage3/**",
                    "/api/stage4/**",
                    "/api/stage5/**",
                    "/api/stage6/**",
                    "/api/stage7/**",
                    "/api/stage8/**"
                ).authenticated()

                // All other unspecified endpoints are also secured
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Enables HTTP Basic authentication

        return http.build();
    }

    // Required for password encoding and decoding during login/register
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
