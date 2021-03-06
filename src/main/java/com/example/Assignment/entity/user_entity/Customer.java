package com.example.Assignment.entity.user_entity;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;


    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false, name = "email", unique = true)
    private String email;

    @Column(nullable = false, name = "role", columnDefinition = "varchar(255) default 'ROLE_USER'")
    private String role;


    public Customer(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Customer() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
