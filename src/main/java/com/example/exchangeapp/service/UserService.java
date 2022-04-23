package com.example.exchangeapp.service;

import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.DTO.UserDTO;
import com.example.exchangeapp.constant.Rate;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Сервис для взаимодействия с личным кабинетом пользователя.
 */
public interface UserService {

    /**
     * Поменять имя пользователя.
     *
     * @param userId  the user id
     * @param newInfo новые данные
     * @return обновлённая сущность пользователя
     */
    Mono<UserDTO> changeName(String userId, PersonalInfoDTO newInfo);

    /**
     * Операция траты.
     *
     * @param userId Идентификатор пользователя
     * @param price  сумма трат
     * @return ничего mono
     */
    Mono<UserDTO> buy(String userId, Long price);

    /**
     * Пополнить баланс.
     *
     * @param userId идентификатор пользователя
     * @param amount сумма пополнения
     * @return ничего mono
     */
    Mono<UserDTO> addMoney(String userId, Long amount);

    /**
     * Добавить нового пользователя.
     *
     * @param newUser новый пользователь
     * @return Сохранённая сущность пользователя
     */
    Mono<UserDTO> create(UserDTO newUser);

    /**
     * Удалить пользователя по идентификатору.
     *
     * @param objectId идентификатор пользователя
     * @return ничего mono
     */
    Mono<Void> delete(String objectId);

    /**
     * Полная информация о пользователе.
     *
     * @param userId Идентификатор пользователя
     * @return информация о пользователе
     */
    Mono<UserDTO> userInfo(String userId);

    Mono<ResponseEntity<?>> login(UserDTO userDTO);

    Mono<Double> convert(String userId, Rate from, Rate to, Double amount);
}
