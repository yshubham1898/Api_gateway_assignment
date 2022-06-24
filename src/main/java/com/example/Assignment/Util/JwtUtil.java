package com.example.Assignment.Util;

import com.example.Assignment.Data.MyCustomerPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

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


    //method to generate the token
    public String generateToken(MyCustomerPrincipal myCustomerPrincipal){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, myCustomerPrincipal.getUsername());
    }

    //create a token
    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    //validate token
    public Boolean validateToken(String token, MyCustomerPrincipal myCustomerPrincipal){
        final String username = extractUsername(token);
        return (username.equals(myCustomerPrincipal.getUsername()) && !isTokenExpired(token));
    }




}
