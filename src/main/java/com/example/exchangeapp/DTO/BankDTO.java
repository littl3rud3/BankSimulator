package com.example.exchangeapp.DTO;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель банка")
public class BankDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "Уникальный идентификатор")
    private String id;

    @Schema(description = "Название банка")
    private String name;

    @Schema(description = "Идентификаторы клиентов")
    private List<String> personIds;
}
