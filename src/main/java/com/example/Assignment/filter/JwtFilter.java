package com.example.Assignment.filter;

import com.example.Assignment.Data.CustomerDetails;
import com.example.Assignment.Util.JwtUtil;
import com.example.Assignment.service.implementation.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtFilter extends OncePerRequestFilter {


    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    JwtUtil jwtUtil;


    //the filter have a task to validate a token.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //getting header
        final String header =request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if(header != null && header.startsWith("Bearer ")){

            //storing the jwt in the jwt variable, coz the actual token will start from 7th index after bearer
            jwt = header.substring(7);

            //extracting username from jwt token
            username = jwtUtil.extractUsername(jwt);
        }

        //now we have to check whether any user is already logged in or not
        //this will be achieved using SecurityContextholder

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

           //getting user details for authentication wrap in myCustomerPrincipal object
            CustomerDetails customerDetails = (CustomerDetails) this.customerServiceImpl.loadUserByUsername(username);

            //validate the token with user details in myCustomerPrincipal
            if(jwtUtil.validateToken(jwt,customerDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(customerDetails,null,new ArrayList<>());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //from this username password authentication token we will add new user to security context
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        //pass on the request & response to next chain of filter
        filterChain.doFilter(request,response);
    }
}
