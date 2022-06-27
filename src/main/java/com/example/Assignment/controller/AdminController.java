package com.example.Assignment.controller;

import com.example.Assignment.Util.ResponseUtil;
import com.example.Assignment.entity.ResponseDto;
import com.example.Assignment.entity.UpdateRole;
import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.enums.Role;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    CustomerServiceImpl customerServiceImpl;


    //the api will update the role attribute
    @PostMapping("/user/{id}/role")
    public ResponseEntity getCustomer(@NotNull @PathVariable("id") long id , @Valid @RequestBody UpdateRole role, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseUtil.getResponse(null, HttpStatus.BAD_REQUEST, bindingResult.getAllErrors());
        }
        try {
            Customer customer = this.customerServiceImpl.getCustomer(id,role.getRole().name()).get();
            return ResponseUtil.getResponse(customer, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseUtil.getResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }


    //api to get list of users.
    //admin can get all the list of users.
    @PreAuthorize("hasAuthority(@roles.adminRole)")
    @GetMapping("/all")
    public ResponseEntity getAllCustomer(){

        try {
            List<Customer> customers = customerServiceImpl.getAllCustomer();
            return ResponseUtil.getResponse(customers, HttpStatus.OK, null);
        } catch (Exception e) {
            return ResponseUtil.getResponse(null, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }


}
