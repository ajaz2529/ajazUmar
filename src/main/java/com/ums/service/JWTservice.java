package com.ums.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ums.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTservice {

    private final static String USER_NAME = "username";
    @Value("${jwt.algorithm.key}")
    private String algorithmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryduration}")
    private int expirytime;

    private Algorithm algorithm;
    @PostConstruct
    public void PostConstruct(){
        algorithm= Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(AppUser user){
        return JWT.create()
                .withClaim(USER_NAME, user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expirytime))
                .withIssuer(issuer)
                .sign(algorithm);
    }


    public String getUsername(String token){
        DecodedJWT decoded = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decoded.getClaim(USER_NAME).asString();
    }



}
