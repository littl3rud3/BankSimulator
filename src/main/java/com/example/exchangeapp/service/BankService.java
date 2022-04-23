package com.example.exchangeapp.service;

import com.example.exchangeapp.DTO.BankDTO;
import com.example.exchangeapp.DTO.PersonalInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Сервис для взаимодействия с банками.
 */
public interface BankService {

    /**
     * Список всех клиентов банка.
     *
     * @param bankName название банка
     * @return Список клиентов
     */
    Flux<PersonalInfoDTO> allClients(String bankName);

    /**
     * Добавить новый банк.
     *
     * @param newBank Информация о банке
     * @return Сохранённая сущность банка
     */
    Mono<BankDTO> create(BankDTO newBank);

    /**
     * Поменять название банка.
     *
     * @param objectId Идентификатор банка
     * @param newName     Новое название банка
     * @return Обновлённая сущность банка
     */
    Mono<BankDTO> update(String objectId, String newName);

    /**
     * Удалить банк по идентификатору.
     *
     * @param bankId идентификатор
     * @return пустота
     */
    Mono<Void> delete(String bankId);

    /**
     * Список всех банков.
     *
     * @return список банков
     */
    Flux<BankDTO> getAll();
}
