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
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    CustomerServiceImpl customerServiceImpl;


    //the api will update the role attribute
    @PostMapping("/user/{id}/role")
    public Optional<Customer> getCustomer(@PathVariable("id") long id , @RequestBody UpdateRole role){
        return this.customerServiceImpl.getCustomer(id,role.getRole().name());
    }


    //api to get list of users.
    //admin can get all the list of users.
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<Customer> getAllCustomer(){
        return customerServiceImpl.getAllCustomer();
    }
}
