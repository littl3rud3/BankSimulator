package com.example.exchangeapp.model;

import java.util.List;

import lombok.Data;
import org.jeasy.random.annotation.Exclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Bank {

    @Id
    @Exclude
    private String id;

    private String name;

    private List<String> personIds;

    public List<String> getAuthorities() {

        return List.of();
    }
}
