package com.projectmanagement.controller;

import com.projectmanagement.dto.AuthRequestDTO;
import com.projectmanagement.dto.AuthResponseDTO;
import com.projectmanagement.dto.UserDTO;
import com.projectmanagement.service.AuthService;
import com.projectmanagement.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            String message = authService.register(userDTO);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                                 .body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO loginRequest,
            HttpServletResponse response
    ) {
        // 1) Authenticate credentials
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
            )
        );

        // 2) Generate JWT
        String token = jwtTokenProvider.generateToken(authentication);

        // 3) Build an HttpOnly cookie
        ResponseCookie cookie = ResponseCookie.from("JWT_TOKEN", token)
            .httpOnly(true)
            .secure(true)               // set to false if not using HTTPS locally
            .path("/")                  
            .maxAge(3 * 24 * 60 * 60)   // e.g. 3 days in seconds
            .sameSite("Strict")         // protects against CSRF
            .build();

        // 4) Add Set-Cookie header
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        // 5) Also return token in body if you need it on the client
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
