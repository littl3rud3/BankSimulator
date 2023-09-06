package com.example.exchangeapp.config;

import static com.example.exchangeapp.constant.Constants.ACCESS_KEY;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class ExchangeFeignConfig {
    
    @Bean
    public RequestInterceptor requestInterceptor(@Value("${access_key}") String accessKey) {
        
        return requestTemplate -> requestTemplate.query(ACCESS_KEY, accessKey);
    }
}
