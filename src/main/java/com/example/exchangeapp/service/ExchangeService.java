package com.example.exchangeapp.service;

import java.util.Map;

import reactor.core.publisher.Mono;

/**
 * Сервис обмена валют.
 */
public interface ExchangeService {

    /**
     * Обменять валюту.
     *
     * @param from   из какой валюты
     * @param to     в какую валюту
     * @param amount какое кол-во
     * @return рез-тат конвертации
     */
    Mono<Double> exchange(String from, String to, Double amount);

    /**
     * Получить курс обмена.
     *
     * @param from из какой валюты
     * @param to   в какую волюту
     * @return курс обмена
     */
    Mono<Double> getRate(String from, String to);

    /**
     * Получить список всех курсов по отношению к введённой.
     *
     * @param from из какой валюты
     * @return все курсы по отношению к введённой валюте
     */
    Mono<Map<String, Double>> getAllCurrencies(String from);
}
