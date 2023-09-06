package com.example.exchangeapp.DTO;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Information for the user's personal account")
public class UserDTO {

    @JsonProperty(access = READ_ONLY)
    @Schema(description = "ID")
    private String id;

    @Schema(description = "Login")
    private String login;

    @JsonProperty(access = WRITE_ONLY)
    @Schema(description = "Password")
    private String password;

    @Schema(description = "Balance")
    private Long amount;

    @Schema(description = "Personal info")
    private PersonalInfoDTO personalInfoId;
}
