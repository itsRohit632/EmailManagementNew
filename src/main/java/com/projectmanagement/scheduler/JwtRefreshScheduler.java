// src/main/java/com/projectmanagement/scheduler/JwtRefreshScheduler.java
package com.projectmanagement.scheduler;

import com.projectmanagement.model.User;
import com.projectmanagement.repo.UserRepository;
import com.projectmanagement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtRefreshScheduler {

    private final UserRepository userRepo;
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtProvider;

    @Autowired
    public JwtRefreshScheduler(UserRepository userRepo,
                               UserDetailsService userDetailsService,
                               JwtTokenProvider jwtProvider) {
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Every day at 1 PM America/New_York, regenerate tokens for admins.
     */
    @Scheduled(cron = "0 0 13 * * *", zone = "America/New_York")
    public void refreshAdminTokens() {
        List<User> admins = userRepo.findAll().stream()
            .filter(u -> u.getRoles().stream()
                .anyMatch(r -> r.getName().name().equals("ROLE_ADMIN")))
            .toList();

        for (User admin : admins) {
            UserDetails ud = userDetailsService.loadUserByUsername(admin.getEmail());
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    ud, ud.getPassword(), ud.getAuthorities());
            String token = jwtProvider.generateToken(auth);
            // TODO: persist or distribute this token as needed
            System.out.println("New admin token for " + admin.getEmail() + ": " + token);
        }
    }

    /**
     * Every 3 days at 1 PM America/New_York, regenerate tokens for regular users.
     */
    @Scheduled(cron = "0 0 13 */3 * *", zone = "America/New_York")
    public void refreshUserTokens() {
        List<User> users = userRepo.findAll().stream()
            .filter(u -> u.getRoles().stream()
                .anyMatch(r -> r.getName().name().equals("ROLE_USER")))
            .toList();

        for (User user : users) {
            UserDetails ud = userDetailsService.loadUserByUsername(user.getEmail());
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    ud, ud.getPassword(), ud.getAuthorities());
            String token = jwtProvider.generateToken(auth);
            // TODO: persist or distribute this token as needed
            System.out.println("New user token for " + user.getEmail() + ": " + token);
        }
    }
}
