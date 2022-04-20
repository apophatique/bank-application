package com.sbrf.reboot.bankapplication.service.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Class that represents JWT token.
 */
class JwtToken extends AbstractAuthenticationToken {
    private final String token;

    /**
     * Class constructor.
     *
     * @param token JWT token string representation.
     */
    JwtToken(final String token) {
        super(null);
        this.token = token;
        super.setAuthenticated(false);
    }

    /**
     * Method that sets "authenticated" parameter of JWT token.
     *
     * @param authenticated New value of "authenticated" parameter of JWT token.
     */
    @Override
    public void setAuthenticated(final boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Can not mark this token as trusted");
        }

        super.setAuthenticated(false);
    }

    /**
     * Credentials getter.
     *
     * In that implementation it equals JWT token string representation.
     *
     * @return Credentials.
     */
    @Override
    public Object getCredentials() {
        return token;
    }

    /**
     * Principal getter.
     *
     * In that implementation it equals null.
     *
     * @return Principal.
     */
    @Override
    public Object getPrincipal() {
        return null;
    }
}
