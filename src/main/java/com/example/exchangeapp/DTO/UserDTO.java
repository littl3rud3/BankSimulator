package com.example.exchangeapp.DTO;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация для личного кабинета пользователя")
public class UserDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "Уникальный идентификатор")
    private String id;

    @Schema(description = "Логин")
    private String login;

    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Пароль")
    private String password;

    @Schema(description = "Баланс")
    private Long amount;

    @Schema(description = "Личная информация")
    private PersonalInfoDTO personalInfoId;
}
