package com.projectmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/user-details/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Or .formLogin(withDefaults()) if you prefer UI login

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            throw new UnsupportedOperationException("Custom UserDetailsService not used.");
        };
    }
}
