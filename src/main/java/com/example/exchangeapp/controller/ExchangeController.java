package com.example.exchangeapp.controller;

import java.util.Map;

import com.example.exchangeapp.service.ExchangeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер курса валют")
@RequestMapping("/api/rate")
public class ExchangeController {

    private final ExchangeService exchangeService;

    @Operation(summary = "Конвертация валюты")
    @GetMapping("/convert")
    public Mono<Double> convert(@RequestParam String from,
                                @RequestParam String to,
                                @RequestParam Double amount) {

        return exchangeService.exchange(from, to, amount);
    }

    @Operation(summary = "Получить курс обмена")
    @GetMapping("/{from}/{to}")
    public Mono<Double> getRate(@PathVariable String from,
                                @PathVariable String to) {

        return exchangeService.getRate(from, to);
    }

    @Operation(summary = "Список всех курсов по отношению к введённой валюте")
    @GetMapping("/{from}")
    public Mono<Map<String, Double>> getAllCurrencies(@PathVariable String from) {

        return exchangeService.getAllCurrencies(from);
    }
}
