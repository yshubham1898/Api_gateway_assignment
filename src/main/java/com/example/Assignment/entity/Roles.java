package com.example.Assignment.entity;

import com.example.Assignment.enums.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Roles {

    public List<String> getAllRoles() {
        return Arrays.stream(Role.values())
                .map(r -> r.name())
                .collect(Collectors.toList());
    }

    public String getAdminRole() {
        return Role.ROLE_ADMIN.name();
    }

    public String getUserRole() {
        return Role.ROLE_USER.name();
    }
}
