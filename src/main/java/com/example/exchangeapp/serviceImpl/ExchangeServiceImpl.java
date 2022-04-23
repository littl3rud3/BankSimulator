package com.example.exchangeapp.serviceImpl;

import java.util.Map;

import com.example.exchangeapp.DTO.feign.ExchangeResponseDTO;
import com.example.exchangeapp.feign.ExchangeFeign;
import com.example.exchangeapp.service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeFeign exchangeFeign;

    @Value("${access_key}")
    private String accessKey;

    @Override
    public Mono<Double> exchange(String from, String to, Double amount) {
        Mono<ExchangeResponseDTO> exchange = exchangeFeign.exchange(accessKey, from, to);

        return exchange.map(exchangeResponseDTO -> exchangeResponseDTO.getRates().get(to) * amount);
    }

    @Override
    public Mono<Double> getRate(String from, String to) {
        Mono<ExchangeResponseDTO> exchange = exchangeFeign.exchange(accessKey, from, to);

        return exchange.map(exchangeResponseDTO -> exchangeResponseDTO.getRates().get(to));
    }

    @Override
    public Mono<Map<String, Double>> getAllCurrencies(String from) {
        Mono<ExchangeResponseDTO> exchange = exchangeFeign.exchange(accessKey, from, null);

        return exchange.map(ExchangeResponseDTO::getRates);
    }
}
