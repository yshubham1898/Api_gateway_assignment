package com.example.Assignment.service.implementation;




import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.enums.Role;
import com.example.Assignment.repo.CustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl{

    @Autowired
    CustomerRepo customerRepo;

    public Customer signup(Customer customer){
        return customerRepo.save(customer);
    }

    //logic to get all customers
    public List<Customer> getAllCustomer() {
        return customerRepo.findAll();
    }

    public Optional<Customer> getCustomer(long id, String role){
        Optional<Customer> customerInfo = customerRepo.findById(id);
//        customerInfo.get().setRole(role);
        customerInfo.ifPresent(user->user.setRole(role));
        customerRepo.saveAndFlush(customerInfo.get());
        return customerInfo;
    }


}
