package com.example.Assignment.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class MyCustomerPrincipal extends User {

    private long userId;

    private String username;


    public MyCustomerPrincipal(long userId, String username, String password) {
        super(username,password , new ArrayList<>());
        this.userId = userId;
        this.username = username;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
