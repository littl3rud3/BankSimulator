package com.example.exchangeapp.feign;

import com.example.exchangeapp.DTO.feign.ExchangeResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * Клиент для обращения к API по обмену валюты.
 */
@ReactiveFeignClient(url = "${exchange_api_url}", name = "exchangeFeign")
public interface ExchangeFeign {

    /**
     * Получить курс обмена.
     *
     * @param accessKey Токен для доступа к API
     * @param from      Из какой валюты переводить
     * @param to        В какую (опционально)
     * @return Мапа - название валюты : курс
     */
    @GetMapping("/v1/latest")
    Mono<ExchangeResponseDTO> exchange(@RequestParam("access_key") String accessKey,
                                       @RequestParam("base") String from,
                                       @RequestParam(value = "symbols", required = false) String to);
}
