package com.example.Assignment.controller;

import com.example.Assignment.entity.Customer;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class UserController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;


    @PostMapping("/save")
    public Customer saveUser(@RequestBody Customer customer){
        return customerServiceImpl.addCustomer(customer);
    }


    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        List<Customer> customers =  customerServiceImpl.getAllCustomer();
        return customers;
    }

}
