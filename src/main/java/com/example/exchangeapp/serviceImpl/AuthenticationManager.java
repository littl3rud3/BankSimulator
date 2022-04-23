package com.example.exchangeapp.serviceImpl;

import java.util.List;

import com.example.exchangeapp.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {

        String authToken = authentication.getCredentials().toString();

        String username;

        try {
            username = JwtUtil.extractUsername(authToken);
        } catch (Exception e) {
            username = null;
            System.out.println(e);
        }

        if (username != null && JwtUtil.validateToken(authToken)) {
            Claims claims = JwtUtil.getClaimsFromToken(authToken);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                null,
                claims.get("role", List.class)
            );

            return Mono.just(authenticationToken);
        } else {
            return Mono.empty();
        }
    }
}
