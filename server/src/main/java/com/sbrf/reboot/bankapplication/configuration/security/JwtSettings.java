package com.sbrf.reboot.bankapplication.configuration.security;

import lombok.Getter;

import java.time.Duration;

/**
 * Класс, содержащий информацию о JWT-токене:
 * - длительность токена
 * - подпись
 * - холдер токена
 */
public class JwtSettings {
    @Getter
    private final String tokenIssuer;
    @Getter
    private final String tokenSigningKey;
    @Getter
    private final int aTokenDuration;

    /**
     * Конструктор класса.
     *
     * @param tokenIssuer     -
     * @param tokenSigningKey -
     * @param aTokenDuration  -
     */
    public JwtSettings(final String tokenIssuer,
                       final String tokenSigningKey,
                       final int aTokenDuration) {
        this.tokenIssuer = tokenIssuer;
        this.tokenSigningKey = tokenSigningKey;
        this.aTokenDuration = aTokenDuration;
    }

    /**
     * Получить длительность токена.
     *
     * @return {@link Duration} длительность токена.
     */
    public Duration getTokenExpiredIn() {
        return Duration.ofMinutes(aTokenDuration);
    }
}
