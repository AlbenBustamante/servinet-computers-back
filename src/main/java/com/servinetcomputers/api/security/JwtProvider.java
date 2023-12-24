package com.servinetcomputers.api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.servinetcomputers.api.dto.response.CampusResponse;
import com.servinetcomputers.api.dto.response.UserResponse;
import com.servinetcomputers.api.exception.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Json Web Token utils.
 */
@Component
public class JwtProvider {

    private static final long expirationTime = TimeUnit.DAYS.toMillis(1);
    private static Algorithm algorithm;
    private static Map<String, CampusResponse> campusTokens;
    private static Map<String, UserResponse> userTokens;

    @Value("${jwt.secret-key}")
    private String secretKey;

    /**
     * Create and register a new jwt.
     * <p>Must to provide only ONE attribute.</p>
     *
     * @param user   the user if it's logging a user.
     * @param campus the campus if it's logging a campus.
     * @return the jwt.
     */
    public String create(final UserResponse user, final CampusResponse campus) {
        final var token = JWT.create()
                .withSubject(user == null ? campus.getTerminal() : user.getEmail())
                .withIssuer("servinet-computers")
                .withClaim("id", user == null ? campus.getId() : user.getId())
                .withClaim("type", user == null ? "c" : "u")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(algorithm());

        if (user != null) {
            userTokens().put(token, user);
        } else {
            campusTokens().put(token, campus);
        }

        return token;
    }

    /**
     * Validates the token and then gets the user/campus authentication.
     *
     * @param token the token provided.
     * @return a {@link UsernamePasswordAuthenticationToken} as the user/campus {@link Authentication}
     */
    public Authentication validateToken(final String token) {
        JWT.require(algorithm()).build().verify(token);

        final var user = userTokens().get(token);
        final var campus = campusTokens().get(token);

        if (user == null && campus == null) {
            throw new AuthenticationException("The user or campus doesn't exists.", "TOKEN PROVIDED NOT FOUND.");
        }

        final Set<GrantedAuthority> authorities = new HashSet<>();
        final var authority = user == null ? "ROLE_CAMPUS" : "ROLE_" + user.getRole();

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
        if (!userTokens().containsKey(token) && !campusTokens().containsKey(token)) {
            return false;
        }

        userTokens().remove(token);
        campusTokens().remove(token);

        return true;
    }

    /**
     * @return the default algorithm to use.
     */
    private Algorithm algorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC256(secretKey);
        }

        return algorithm;
    }

    /**
     * @return the campuses with its tokens.
     */
    private Map<String, CampusResponse> campusTokens() {
        if (campusTokens == null) {
            campusTokens = new HashMap<>();
        }

        return campusTokens;
    }

    /**
     * @return the users with its tokens.
     */
    private Map<String, UserResponse> userTokens() {
        if (userTokens == null) {
            userTokens = new HashMap<>();
        }

        return userTokens;
    }

}
