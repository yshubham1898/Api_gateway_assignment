package com.example.Assignment.controller;

import com.example.Assignment.Util.JwtUtil;
import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.entity.jwt_entity.JwtRequest;
import com.example.Assignment.entity.jwt_entity.JwtResponse;
import com.example.Assignment.enums.Role;
import com.example.Assignment.service.implementation.CustomerDetailsService;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    CustomerDetailsService customerDetails;

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;


    //api to authenticate user
    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                                    jwtRequest.getPassword()));
        }
        catch (Exception e){
            System.out.println(e);
        }

        final UserDetails userDetails = customerDetails.loadUserByUsername(jwtRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    //api to register users
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Customer signup(@RequestBody JwtRequest jwtRequest){
        Customer customer = new Customer();
        customer.setUsername(jwtRequest.getUsername());
        customer.setPassword(passwordEncoder.encode(jwtRequest.getPassword()));
        customer.setEmail(jwtRequest.getPassword());
        customer.setRole(Role.ROLE_USER.name());
        return customerServiceImpl.signup(customer);
    }
}
