package com.example.exchangeapp.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import com.example.exchangeapp.DTO.feign.ExchangeResponseDTO;
import com.example.exchangeapp.feign.ExchangeFeign;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
class ExchangeServiceImplTest {

    @Mock
    private ExchangeFeign exchangeFeign;

    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    private static ExchangeResponseDTO exchangeResponseDTOStub;

    private static Double amount;

    @BeforeAll
    private static void iii() {

        final EasyRandom EASY_RANDOM = new EasyRandom();

        exchangeResponseDTOStub = EASY_RANDOM.nextObject(ExchangeResponseDTO.class);
        amount = EASY_RANDOM.nextObject(Double.class);
    }

    @Test
    void exchange() {

        //toDo
        when(exchangeFeign.exchange(anyString(), anyString(), anyString())).thenReturn(Mono.just(exchangeResponseDTOStub));

        Mono<Double> exchange = exchangeService.exchange(anyString(), anyString(), amount);

        assertEquals(exchange.map(ex -> ex % amount).subscribe(), Mono.just((double) 0).subscribe());
    }

    @Test
    void getRate() {

        when(exchangeFeign.exchange(anyString(), anyString(), anyString())).thenReturn(Mono.just(exchangeResponseDTOStub));

        Mono<Double> rate = exchangeService.getRate(anyString(), anyString());

        assertNotNull(rate);
        verify(exchangeFeign).exchange(anyString(), anyString(), anyString());
    }

    @Test
    void getAllCurrencies() {

        when(exchangeFeign.exchange(anyString(), anyString(), anyString())).thenReturn(
            Mono.just(exchangeResponseDTOStub));

        Mono<Map<String, Double>> allCurrencies = exchangeService.getAllCurrencies(anyString());

        assertNotNull(allCurrencies);
    }
}