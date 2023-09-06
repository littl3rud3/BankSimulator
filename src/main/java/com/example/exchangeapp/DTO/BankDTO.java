package com.example.exchangeapp.DTO;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Bank")
public class BankDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "ID")
    private String id;

    @Schema(description = "name")
    private String name;

    @Schema(description = "Customer IDs")
    private List<String> personIds;
}
