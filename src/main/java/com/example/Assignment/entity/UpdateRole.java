package com.example.Assignment.entity;


import com.example.Assignment.enums.Role;

import javax.validation.constraints.NotNull;

public class UpdateRole {

    @NotNull
    private Role role;


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
