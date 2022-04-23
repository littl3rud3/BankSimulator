package com.example.exchangeapp.repository;

import com.example.exchangeapp.model.Bank;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface BankRepository extends ReactiveCrudRepository<Bank, String> {

    Mono<Bank> findByName(String name);
}
