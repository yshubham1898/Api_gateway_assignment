package com.example.Assignment.service.implementation;

import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    CustomerRepo customerRepo;



    //overridden method of UserDetailsService interface
    //this will return the whole user details for authentication
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //getting username from database
        Customer customerDetails = customerRepo.findByUsername(username);
        if(customerDetails == null){
            throw new UsernameNotFoundException("User not found with username : "+username);
        }
        return new CustomerDetails(customerDetails);
    }
}
