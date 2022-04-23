package com.example.exchangeapp.model;

import java.util.List;

import lombok.Data;

@Data
public class PersonalInfo {

    private String lastName;

    private String firstName;

    private String midName;

    private List<String> bankIds;
}
