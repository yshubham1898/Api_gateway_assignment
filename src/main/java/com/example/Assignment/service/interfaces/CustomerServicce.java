package com.example.Assignment.service.interfaces;

import com.example.Assignment.entity.Customer;

import java.util.List;

public interface CustomerServicce {

    public Customer addCustomer(Customer customer);

    public List<Customer> getAllCustomer();


}
