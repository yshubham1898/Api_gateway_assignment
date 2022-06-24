package com.example.Assignment.service.implementation;


import com.example.Assignment.Data.CustomerDto;
import com.example.Assignment.Data.CustomerDetails;
import com.example.Assignment.entity.Customer;
import com.example.Assignment.repo.CustomerRepo;

import com.example.Assignment.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //overridden method of UserDetailsService interface
    //this will return the whole user details for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //getting username from database
        Customer customer = customerRepo.findByUsername(username);
        if(customer == null){
            throw new UsernameNotFoundException("User not found with username : "+username);
        }
        return new CustomerDetails(customer.getId(),customer.getUsername(),customer.getPassword());
    }


    //logic to add new user to database
    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        return customerRepo.save(customer);
    }


    @Override
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }


}
