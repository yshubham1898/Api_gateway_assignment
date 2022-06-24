package com.example.Assignment.Data;

import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

public class CustomerDetails extends User {

    private long userId;

    private String username;


    public CustomerDetails(long userId, String username, String password) {
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
