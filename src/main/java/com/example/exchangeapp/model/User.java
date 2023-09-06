package com.example.exchangeapp.model;

import java.util.List;

import lombok.Data;
import org.jeasy.random.annotation.Exclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document
@Data
public class User implements UserDetails {

    @Id
    @Exclude
    private String id;

    private String login;

    private String password;

    private Double rubAcc;

    private Double usdAcc;

    private Double eurAcc;

    private String userId;

    private List<String> privileges;

    /* !Encapsulation, it would be more correct to make a static class, but Mongock 5.0.38 does not support */
    private PersonalInfo personalInfo;
    
    /* Mangock creates a List(Map), because of this, role parsing does not work with JWT authorization */
    /* For correct authorization, you need to disable migrations and create collections manually */
    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {

        return this.privileges
            .stream()
            .map(SimpleGrantedAuthority::new)
            .toList();
    }

    @Transient
    @Override
    public String getUsername() {

        return this.login;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Transient
    @Override
    public boolean isEnabled() {

        return true;
    }
}
