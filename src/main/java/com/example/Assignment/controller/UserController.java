package com.example.Assignment.controller;

import com.example.Assignment.Util.ResponseUtil;
import com.example.Assignment.entity.ResponseDto;
import com.example.Assignment.entity.UpdateRole;
import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CustomerServiceImpl customerServiceImpl;


    //it is a user api only authorised to user
    @GetMapping("/helloUser")
    public ResponseEntity user(){
        return ResponseUtil.getResponse("high user!!", HttpStatus.OK, null);
    }


}
