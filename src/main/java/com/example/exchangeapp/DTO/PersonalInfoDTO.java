package com.example.exchangeapp.DTO;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Личные данные человека")
public class PersonalInfoDTO {

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Отчество")
    private String midName;

    @Schema(description = "Идентификаторы банков")
    private List<String> bankIds;

}
