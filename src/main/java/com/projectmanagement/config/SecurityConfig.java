package com.projectmanagement.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;
//
//@Configuration
//public class SecurityConfig {
//
//        @Bean
//        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//            http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorizeHttpRequests ->
//                    authorizeHttpRequests
//                        .requestMatchers("/api/auth/**", "/api/user-details/**").permitAll() // No authentication required
//                        .requestMatchers("/api/stage2/**").permitAll() // No authentication required for stage 2
//                        .requestMatchers("/api/stage2/**", "/api/stage3/**", "/api/stage4/**", "/api/stage5/**",
//                                         "/api/stage6/**", "/api/stage7/**", "/api/stage8/**").authenticated() // Authentication required for these endpoints
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()); // Use HTTP basic authentication with defaults
//
//            return http.build();
//        }
//}


import com.projectmanagement.security.JwtAuthenticationFilter; // Ensure this is the correct package for CustomUserDetailsService
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/auth/**").permitAll() // Public endpoints
                    .anyRequest().authenticated() // Secure other endpoints
                )
                .sessionManagement(sessionManagement -> 
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // No sessions

        // Add JWT filter before Spring Security's UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
