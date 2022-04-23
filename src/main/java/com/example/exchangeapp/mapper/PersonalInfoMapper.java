package com.example.exchangeapp.mapper;

import java.util.List;

import com.example.exchangeapp.DTO.PersonalInfoDTO;
import com.example.exchangeapp.model.PersonalInfo;
import org.mapstruct.Mapper;

/**
 * Сервис конвертирования PersonalInfoDTO в PersonalInfoEntity.
 */
@Mapper
public interface PersonalInfoMapper {

    /**
     * Конвертировать DTO в Entity.
     *
     * @param personalInfoDTO
     * @return PersonalInfoEntity
     */
    PersonalInfo toEntity(PersonalInfoDTO personalInfoDTO);

    /**
     * Конвертировать Entity в DTO.
     *
     * @param personalInfoEntity
     * @return PersonalInfoDTO
     */
    PersonalInfoDTO toDTO(PersonalInfo personalInfoEntity);

    /**
     * Конвертировать массив PersonalInfoEntity в PersonalInfoDTO.
     *
     * @param personalInfoEntities массив BankEnity
     * @return Массив PersonalInfoDTO
     */
    List<PersonalInfoDTO> toDTOList(List<PersonalInfo> personalInfoEntities);

    /**
     * Конвертировать массив PersonalInfoDTO в PersonalInfoEntity.
     *
     * @param personalInfoDTOList массив PersonalInfoDTO
     * @return Массив PersonalInfoEntity
     */
    List<PersonalInfo> toUserEntityList(List<PersonalInfoDTO> personalInfoDTOList);
}
