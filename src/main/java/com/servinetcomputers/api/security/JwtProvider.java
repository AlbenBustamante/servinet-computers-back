package com.servinetcomputers.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.servinetcomputers.api.util.constants.SecurityConstants.CAMPUS_AUTHORITY;
import static com.servinetcomputers.api.util.constants.SecurityConstants.getAuthority;

/**
 * Json Web Token utils.
 */
@Component
public class JwtProvider {

    private static final long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);
    private final Map<String, CampusResponse> campusTokens;
    private final Map<String, UserResponse> userTokens;

    @Value("${jwt.secret-key}")
    private String secretKey;

    public JwtProvider() {
        this.campusTokens = new HashMap<>();
        this.userTokens = new HashMap<>();
    }

    /**
     * Create and get a new user JWT.
     *
     * @param user the user.
     * @return the JWT.
     */
    public String create(final UserResponse user) {
        final var token = create(user.getId(), user.getEmail(), AuthTokenType.USER);
        userTokens.put(token, user);

        return token;
    }

    /**
     * Create and get a new campus JWT.
     *
     * @param campus the campus.
     * @return the JWT.
     */
    public String create(final CampusResponse campus) {
        final var token = create(campus.getId(), campus.getTerminal(), AuthTokenType.CAMPUS);
        campusTokens.put(token, campus);

        return token;
    }

    /**
     * Create and get a new JWT.
     *
     * @param id       the auth id.
     * @param username the auth username.
     * @param type     the auth type.
     * @return the JWT.
     */
    private String create(final int id, final String username, final AuthTokenType type) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("servinet-computers")
                .withClaim("id", id)
                .withClaim("type", type.toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(secretKey));
    }

    /**
     * Validates the token and then gets the user/campus authentication.
     *
     * @param token the token provided.
     * @return a {@link UsernamePasswordAuthenticationToken} as the user/campus {@link Authentication}
     */
    public Authentication validateToken(final String token) {
        try {
            JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token);
        } catch (Exception ex) {
            throw new AuthenticationException(ex.getMessage(), "EXCEPTION VALIDATIN THE TOKEN");
        }

        final var user = userTokens.get(token);
        final var campus = campusTokens.get(token);

        if (user == null && campus == null) {
            throw new AuthenticationException("The user or campus doesn't exists.", "TOKEN PROVIDED NOT FOUND.");
        }

        final Set<GrantedAuthority> authorities = new HashSet<>();
        final var authority = user == null ? CAMPUS_AUTHORITY : getAuthority(user);

        authorities.add(new SimpleGrantedAuthority(authority));

        return new UsernamePasswordAuthenticationToken(user == null ? campus : user, token, authorities);
    }

    /**
     * Remove the token from the token lists.
     *
     * @param token the token provided.
     * @return {@code true} if the token was removed.
     */
    public boolean delete(final String token) {
        if (!userTokens.containsKey(token) && !campusTokens.containsKey(token)) {
            return false;
        }

        userTokens.remove(token);
        campusTokens.remove(token);

        return true;
    }

}
