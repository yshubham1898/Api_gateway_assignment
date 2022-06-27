package com.example.Assignment.controller;

import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;


//    @PostMapping("/save")
//    public Customer saveUser(@RequestBody Customer customer){
//        return customerServiceImpl.addCustomer(customer);
//    }



    //it is a admin api only authorised for admin
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        List<Customer> customers =  customerServiceImpl.getAllCustomer();
        return customers;
    }


    //it is a public api
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello User!!!!";
    }


    //it is a user api only authorised to user
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String user(){
        return "user!!";
    }



}
