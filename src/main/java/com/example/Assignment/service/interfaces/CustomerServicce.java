package com.example.Assignment.service.interfaces;

import com.example.Assignment.Data.CustomerDto;
import com.example.Assignment.entity.Customer;

import java.util.List;

public interface CustomerServicce {

//    public Customer addCustomer(Customer customer);

    Customer saveCustomer(CustomerDto customerDto);

    public List<Customer> getAllCustomer();


}
