package com.example.exchangeapp.repository;

import java.util.Collection;
import java.util.List;

import com.example.exchangeapp.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByLogin(String login);

    Flux<User> findAllByPersonalInfoBankIdsIn(Collection<List<String>> privileges);
}
