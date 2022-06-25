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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login&signup")
public class LoginController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getPassword(),jwtRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect Username & Password!! ", e);
        }
        CustomerDetails customerDetails =
                (CustomerDetails) customerServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String jwt = jwtUtil.generateToken(customerDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping(value = "/signup")
    public Customer signup(@RequestBody CustomerDto customerDto){
        return customerServiceImpl.saveCustomer(customerDto);
    }
}
