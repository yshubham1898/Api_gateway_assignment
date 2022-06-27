package com.example.Assignment.Util;

import com.example.Assignment.entity.user_entity.Customer;
import com.example.Assignment.repo.CustomerRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Autowired
    CustomerRepo customerRepo;

    //secret key is used to create a signature
    private String SECRET_KEY = "secret";

    //retrieve username from jwt token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //extract expiration date from jwt token
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    //
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    //check if token has expired or not
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    //change-----------------------------------------------------------------//
    //method to generate the token
    public String generateToken(UserDetails userDetails) throws Exception{
        Map<String, Object> claims = new HashMap<>();

        Set<String> userRoles = new HashSet<>();
        Customer customer = customerRepo.findByUsername(userDetails.getUsername());
        Arrays.stream(customer.getRole().split(",")).forEach(role -> userRoles.add(role));
        claims.put("Roles",userRoles.toArray());

        return createToken(claims, userDetails.getUsername());
    }
    //---------------------------------------------------------------------//

    //create a token
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



}
