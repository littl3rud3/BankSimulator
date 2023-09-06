package com.example.exchangeapp.service;

import java.util.Map;

import reactor.core.publisher.Mono;

/**
 * Currency exchange service.
 */
public interface ExchangeService {

    /**
     * Exchange.
     *
     * @param from   from which currency
     * @param to     in what currency
     * @param amount how much money to exchange
     * @return conversion result
     */
    Mono<Double> exchange(String from, String to, Double amount);

    /**
     * Get the exchange rate.
     *
     * @param from   from which currency
     * @param to     in what currency
     * @return exchange rate
     */
    Mono<Double> getRate(String from, String to);

    /**
     * Get a list of all courses relative to the one entered.
     *
     * @param from   from which currency
     * @return all rates in relation to the entered currency
     */
    Mono<Map<String, Double>> getAllCurrencies(String from);
}
