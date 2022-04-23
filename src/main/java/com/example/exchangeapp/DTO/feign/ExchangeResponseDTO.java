package com.example.exchangeapp.DTO.feign;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO для получения ответа от фейн клиента обмена валюты.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeResponseDTO {

    /**
     * <Валюта, курс>.
     */
    Map<String, Double> rates;
}
