package com.example.exchangeapp.DTO;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Personal data")
public class PersonalInfoDTO {

    @Schema(description = "Surname")
    private String lastName;

    @Schema(description = "Name")
    private String firstName;

    @Schema(description = "Middle name")
    private String midName;

    @Schema(description = "Bank IDs")
    private List<String> bankIds;

}
