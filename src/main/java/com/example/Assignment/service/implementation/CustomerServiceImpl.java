package com.example.Assignment.service.implementation;


import com.example.Assignment.entity.Customer;
import com.example.Assignment.repo.UserRepo;
import com.example.Assignment.service.interfaces.CustomerServicce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServicce {

    @Autowired
    UserRepo userRepo;


    @Override
    public Customer addCustomer(Customer customer) {
        return userRepo.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return userRepo.findAll();
    }

}
