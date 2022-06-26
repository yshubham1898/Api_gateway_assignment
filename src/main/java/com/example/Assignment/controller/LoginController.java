package com.example.Assignment.controller;

import com.example.Assignment.Data.CustomerDetails;
import com.example.Assignment.Data.CustomerDto;
import com.example.Assignment.Util.JwtUtil;
import com.example.Assignment.entity.Customer;
import com.example.Assignment.entity.jwt_entity.JwtRequest;
import com.example.Assignment.entity.jwt_entity.JwtResponse;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

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

        final CustomerDetails customerDetails =
                (CustomerDetails) customerServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        final String jwt = jwtUtil.generateToken(customerDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    //api to register users
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Customer signup(@RequestBody CustomerDto customerDto){
        return customerServiceImpl.saveCustomer(customerDto);
    }
}
