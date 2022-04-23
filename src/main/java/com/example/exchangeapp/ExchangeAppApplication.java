package com.example.exchangeapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients
//@EnableMongock <---- Не работает
@OpenAPIDefinition(
    info = @Info(
        title = "Exchange application",
        version = "0.0.1",
        description = "Банковское приложение")
)
public class ExchangeAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(ExchangeAppApplication.class, args);
    }
}
