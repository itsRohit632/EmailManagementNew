package com.projectmanagement.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace with your user lookup logic (e.g., database query)
        if ("admin".equals(username)) {
            return User.builder()
                    .username("admin")
                    .password("1234") // NoOpPasswordEncoder for simplicity
                    .roles("ADMIN")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
