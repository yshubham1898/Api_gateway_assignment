package com.example.Assignment.service.interfaces;

import com.example.Assignment.Data.CustomerDto;
import com.example.Assignment.entity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {

//    public Customer addCustomer(Customer customer);

    Customer saveCustomer(CustomerDto customerDto);

    public List<Customer> getAllCustomer();


}
