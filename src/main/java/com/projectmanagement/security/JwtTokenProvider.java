package com.projectmanagement.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.time.ZonedDateTime;
import java.time.ZoneId;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Date now = new Date();

        // Admin vs User expiration logic unchanged
        Date expiry = isAdmin(user)
            ? next1PmEastern()
            : new Date(now.getTime() + 3L * 24 * 60 * 60 * 1000);

        // Create an HmacSHA512 key
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiration(expiry)
                .signWith(key)    // ← use single-arg signWith(Key)
                .compact();
    }

    private boolean isAdmin(UserDetails user) {
        return user.getAuthorities().stream()
                   .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private Date next1PmEastern() {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime next1pm = now.withHour(13).withMinute(0).withSecond(0).withNano(0);
        if (!next1pm.isAfter(now)) next1pm = next1pm.plusDays(1);
        return Date.from(next1pm.toInstant());
    }
}
