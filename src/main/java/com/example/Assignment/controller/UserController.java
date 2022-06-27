package com.example.Assignment.controller;

import com.example.Assignment.entity.UpdateRole;
import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;



    //it is a user api only authorised to user
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/helloUser")
    public String user(){
        return "user!!";
    }






}
