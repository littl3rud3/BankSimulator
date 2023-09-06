package com.example.exchangeapp.feign;

import com.example.exchangeapp.DTO.feign.ExchangeResponseDTO;
import com.example.exchangeapp.config.ExchangeFeignConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * Client for accessing the Currency exchange API.
 */
@ReactiveFeignClient(url = "${exchange_api_url}", name = "exchangeFeign", configuration = ExchangeFeignConfig.class)
public interface ExchangeFeign {

    /**
     * Get the exchange rate.
     *
     * @param from      From which currency to transfer
     * @param to        What currency to transfer to (optional)
     * @return currency name : exchange rate
     */
    @GetMapping("/v1/latest")
    Mono<ExchangeResponseDTO> exchange(@RequestParam("base") String from,
                                       @RequestParam(value = "symbols", required = false) String to);
}
