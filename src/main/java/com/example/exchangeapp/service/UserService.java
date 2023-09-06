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
     * Change the username.
     *
     * @param userId  the user id
     * @param newInfo new data
     * @return updated user entity
     */
    Mono<UserDTO> changeName(String userId, PersonalInfoDTO newInfo);

    /**
     * The operation of spending.
     *
     * @param userId User ID
     * @param price  amount of expenses
     * @return updated user entity
     */
    Mono<UserDTO> buy(String userId, Long price);

    /**
     * Top up balance.
     *
     * @param userId User ID
     * @param amount deposit amount
     * @return updated user entity
     */
    Mono<UserDTO> addMoney(String userId, Long amount);

    /**
     * Add new user.
     *
     * @param newUser new user
     * @return Saved user entity
     */
    Mono<UserDTO> create(UserDTO newUser);

    /**
     * Delete a user by ID.
     *
     * @param objectId User ID
     * @return Nothing
     */
    Mono<Void> delete(String objectId);

    /**
     * Full user Information.
     *
     * @param userId User ID
     * @return User information
     */
    Mono<UserDTO> userInfo(String userId);
    
    /**
     * Authorization.
     *
     * @param userDTO User information
     * @return Information about authorization
     */
    Mono<ResponseEntity<?>> login(UserDTO userDTO);
    
    /**
     * Exchange.
     *
     * @param from   from which currency
     * @param to     in what currency
     * @param amount how much money to exchange
     * @return conversion result
     */
    Mono<Double> convert(String userId, Rate from, Rate to, Double amount);
}
