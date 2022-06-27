package com.example.Assignment.controller;

import com.example.Assignment.Util.JwtUtil;
import com.example.Assignment.Util.ResponseUtil;
import com.example.Assignment.entity.ResponseDto;
import com.example.Assignment.entity.jwt_entity.AuthenticationRequest;
import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.entity.jwt_entity.JwtRequest;
import com.example.Assignment.entity.jwt_entity.JwtResponse;
import com.example.Assignment.enums.Role;
import com.example.Assignment.service.implementation.CustomerDetailsService;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) throws Exception{
        if (bindingResult.hasErrors()) {
            return ResponseUtil.getResponse(null, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }
        try {
            authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                    authenticationRequest.getPassword()));

            final UserDetails userDetails = customerDetails.loadUserByUsername(authenticationRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails);
            JwtResponse jwtResponse = new JwtResponse(jwt);
            return ResponseUtil.getResponse(jwtResponse, HttpStatus.OK, null);
        }
        catch (Exception e){
            System.out.println(e);
            return ResponseUtil.getResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }


    }


    //api to register users
    @PostMapping("/register")
    public ResponseEntity signup(@Valid @RequestBody JwtRequest jwtRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseUtil.getResponse(null, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }
        try {
            Customer customer = new Customer();
            customer.setUsername(jwtRequest.getUsername());
            customer.setPassword(passwordEncoder.encode(jwtRequest.getPassword()));
            customer.setEmail(jwtRequest.getEmail());
            customer.setRole(Role.ROLE_USER.name());
            Customer savedCustomer = customerServiceImpl.signup(customer);
            return ResponseUtil.getResponse(savedCustomer, HttpStatus.OK, null);
        } catch(Exception e) {
            return ResponseUtil.getResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello User!!!!";
    }


}
