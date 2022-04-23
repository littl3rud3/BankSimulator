package com.example.exchangeapp.mapper;

import java.util.List;

import com.example.exchangeapp.DTO.UserDTO;
import com.example.exchangeapp.model.User;
import org.mapstruct.Mapper;

/**
 * Сервис конвертирования UserDTO в UserEntity.
 */
@Mapper
public interface UserMapper {

    /**
     * Конвертировать UserDTO в Entity.
     *
     * @param userDTO
     * @return UserEntity
     */
    User toEntity(UserDTO userDTO);

    /**
     * Конвертировать Entity в DTO.
     *
     * @param userEntity
     * @return UserDTO
     */
    UserDTO toDTO(User userEntity);

    /**
     * Конвертировать массив UserEntity в UserDTO.
     *
     * @param userEntites массив BankEnity
     * @return Массив UserDTO
     */
    List<UserDTO> toDTOList(List<User> userEntites);

    /**
     * Конвертировать массив UserDTO в UserEntity.
     *
     * @param userDTOList массив UserDTO
     * @return Массив UserEntity
     */
    List<User> toUserEntityList(List<UserDTO> userDTOList);
}
