package com.example.exchangeapp.controller;

import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.DTO.UserDTO;
import com.example.exchangeapp.constant.Rate;
import com.example.exchangeapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "Личный кабинет пользователя")
@RequestMapping("/api/lk")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Поменять имя")
    @PutMapping("/{id}")
    public Mono<UserDTO> changeName(@PathVariable String id, @RequestBody PersonalInfoDTO newInfo) {

        return userService.changeName(id, newInfo);
    }

    @Operation(summary = "Личный кабинет")
    @GetMapping("/{userId}")
    public Mono<UserDTO> userInfo(@PathVariable String userId) {

        return userService.userInfo(userId);
    }

    @Operation(summary = "Списание денежных средств")
    @PostMapping("/{userId}/buy")
    public Mono<UserDTO> buy(@PathVariable String userId, @RequestParam Long price) {

        return userService.buy(userId, price);
    }

    @Operation(summary = "Пополнение счёта")
    @PostMapping("/{userId}/add")
    public Mono<UserDTO> addMoney(@PathVariable String userId, @RequestParam Long amount) {

        return userService.addMoney(userId, amount);
    }

    @Operation(summary = "Регистрация нового пользователя")
    @PostMapping("/register")
    public Mono<UserDTO> create(@RequestBody UserDTO userDTO) {

        return userService.create(userDTO);
    }

    @Operation(summary = "Удалить пользователя")
    @DeleteMapping("/{userId}")
    public Mono<Void> delete(@PathVariable String userId) {

        return userService.delete(userId);
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody UserDTO userDTO) {

        return userService.login(userDTO);
    }

    @GetMapping("{userId}/convert/{from}/{to}")
    public Mono<Double> convert(@PathVariable String userId,
                                @PathVariable Rate from,
                                @PathVariable Rate to,
                                @RequestParam Double amount) {

        return userService.convert(userId, from, to, amount);
    }
}
