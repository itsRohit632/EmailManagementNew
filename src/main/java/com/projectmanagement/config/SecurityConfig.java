package com.projectmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/user-details/**").permitAll()
                .requestMatchers("/api/stage2/**").permitAll()
                
             // Protected Endpoints: Stage 2 and beyond
                .requestMatchers(
                    "/api/stage2/**",
                    "/api/stage3/**",
                    "/api/stage4/**",
                    "/api/stage5/**",
                    "/api/stage6/**",
                    "/api/stage7/**",
                    "/api/stage8/**"
                ).authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());

        return http.build();
    }
}
