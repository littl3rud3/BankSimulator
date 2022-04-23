package com.example.exchangeapp.serviceImpl.security;

import com.example.exchangeapp.model.User;
import com.example.exchangeapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        Mono<User> userMono = userRepository.findByLogin(username);
        return userMono.cast(UserDetails.class);
    }
}
