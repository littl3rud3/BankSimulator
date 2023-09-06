package com.example.exchangeapp.utils;

import static com.example.exchangeapp.constant.Constants.ROLE;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * The type Jwt util.
 */
@Component
public class JwtUtil {

    private static final String SECRET = RandomStringUtils.random(50, true, true);

    @Value("${jwt.expiration}")
    private Long expirationTime;

    private static Long EXPIRATION_TIME;

    @Value("${jwt.expiration}")
    public void setExpirationTimeStatic() {

        EXPIRATION_TIME = expirationTime;
    }

    /**
     * Extract username string.
     *
     * @param authToken the auth token
     * @return the string
     */
    public static String extractUsername(String authToken) {

        return getClaimsFromToken(authToken)
            .getSubject();
    }

    /**
     * Gets claims from token.
     *
     * @param authToken the auth token
     * @return the claims from token
     */
    public static Claims getClaimsFromToken(String authToken) {

        String key = Base64.getEncoder().encodeToString(SECRET.getBytes());

        return Jwts.parserBuilder()
                   .setSigningKey(key)
                   .build()
                   .parseClaimsJws(authToken)
                   .getBody();
    }

    /**
     * Validate token boolean.
     *
     * @param authToken the auth token
     * @return the boolean
     */
    public static boolean validateToken(String authToken) {

        return getClaimsFromToken(authToken)
            .getExpiration()
            .after(new Date());
    }

    /**
     * Generate token string.
     *
     * @param user the user
     * @return the string
     */
    public static String generateToken(UserDetails user) {

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(ROLE, user.getAuthorities());

        long expirationSeconds = EXPIRATION_TIME;
        Date creationDate = new Date();
        Date expirationDate = new Date(creationDate.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(user.getUsername())
                   .setIssuedAt(creationDate)
                   .setExpiration(expirationDate)
                   .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                   .compact();
    }
}
