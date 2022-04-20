package com.sbrf.reboot.bankapplication.service.security;

import com.sbrf.reboot.bankapplication.entities.User;
import com.sbrf.reboot.bankapplication.configuration.security.JwtSettings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.Date;

public class JwtService implements IJwtService {
    private final JwtSettings settings;

    public JwtService(final JwtSettings settings) {
        this.settings = settings;
    }

    @Override
    public String createToken(final User user) {
        Instant now = Instant.now();

        Claims claims = Jwts.claims()
                .setIssuer(settings.getTokenIssuer())
                .setIssuedAt(Date.from(now))
                .setSubject(user.getUsername())
                .setExpiration(Date.from(now.plus(settings.getTokenExpiredIn())));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, settings.getTokenSigningKey())
                .compact();
    }

    @Override
    public Authentication parseToken(String token) {
        final Jws<Claims> claims = Jwts.parser()
                .setSigningKey(settings.getTokenSigningKey())
                .parseClaimsJws(token);

        final String subject = claims.getBody().getSubject();

        return new AuthenticatedJwtToken(subject);
    }
}
