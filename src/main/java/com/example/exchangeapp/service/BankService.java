package com.example.exchangeapp.service;

import com.example.exchangeapp.DTO.BankDTO;
import com.example.exchangeapp.DTO.PersonalInfoDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A service for interacting with banks.
 */
public interface BankService {

    /**
     * A list of all the bank's clients.
     *
     * @param bankName Bank name
     * @return List of clients
     */
    Flux<PersonalInfoDTO> allClients(String bankName);

    /**
     * Add new bank.
     *
     * @param newBank Information about the bank
     * @return Saved Bank Entity
     */
    Mono<BankDTO> create(BankDTO newBank);

    /**
     * Change bank name.
     *
     * @param objectId Bank ID
     * @param newName     New bank name
     * @return Saved Bank Entity
     */
    Mono<BankDTO> update(String objectId, String newName);

    /**
     * Delete a bank by ID.
     *
     * @param bankId Bank ID
     * @return Nothing
     */
    Mono<Void> delete(String bankId);

    /**
     * List of all banks.
     *
     * @return Bank list
     */
    Flux<BankDTO> getAll();
}
