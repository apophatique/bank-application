package com.sbrf.reboot.bankapplication.configuration.security;

import com.sbrf.reboot.bankapplication.service.security.IJwtService;
import com.sbrf.reboot.bankapplication.service.security.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    final String tokenIssuer;
    final String tokenSigningKey;
    final int aTokenDuration;

    public JwtConfig(@Value("${jwt.issuer}") final String tokenIssuer,
                     @Value("${jwt.signingKey}") final String tokenSigningKey,
                     @Value("${jwt.aTokenDuration}") final int aTokenDuration) {
        this.tokenIssuer = tokenIssuer;
        this.tokenSigningKey = tokenSigningKey;
        this.aTokenDuration = aTokenDuration;
    }

    @Bean
    public JwtSettings jwtSettings() {
        return new JwtSettings(tokenIssuer, tokenSigningKey, aTokenDuration);
    }

    @Bean
    public IJwtService jwtService(final JwtSettings jwtSettings) {
        return new JwtService(jwtSettings);
    }
}
