package com.example.exchangeapp.mapper;

import java.util.List;

import com.example.exchangeapp.DTO.BankDTO;
import com.example.exchangeapp.model.Bank;
import org.mapstruct.Mapper;

/**
 * Сервис конвертирования BankDTO в BankEntity.
 */
@Mapper
public interface BankMapper {

    /**
     * Конвертировать DTO в Entity.
     *
     * @param bankDTO
     * @return BankEntity
     */
    Bank toEntity(BankDTO bankDTO);

    /**
     * Конвертировать Entity в DTO.
     *
     * @param bankEntity
     * @return BankDTO
     */
    BankDTO toDTO(Bank bankEntity);

    /**
     * Конвертировать массив BankEntity в BankDTO.
     *
     * @param bankEntities массив BankEnity
     * @return Массив BankDTO
     */
    List<BankDTO> toDTOList(List<Bank> bankEntities);

    /**
     * Конвертировать массив BankDTO в BankEntity.
     *
     * @param bankDTOS массив BankDTO
     * @return Массив BankEntity
     */
    List<Bank> toUserEntityList(List<BankDTO> bankDTOS);
}
