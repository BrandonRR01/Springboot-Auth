package com.proyectos.authservice.services.impl;

import com.proyectos.authservice.commons.dto.TokenResponse;
import com.proyectos.authservice.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {


    private final SecretKey secretKey ;

    public JwtServiceImpl() {
        this.secretKey = Jwts.SIG.HS512.key().build();
    }

    @Override
    public TokenResponse generateToken(Long userId) {

        Date expirationDate = new Date(Long.MAX_VALUE);

        String token = Jwts.builder()
                .subject(String.valueOf(userId))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .signWith(this.secretKey)
                .compact();

        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public Claims getClaims(String token) {

        return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    @Override
    public boolean isExpired(String token) {
        try{
            return getClaims(token).getExpiration().before(new Date());
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Integer extractUserId(String token) {
        try{
            return Integer.parseInt(getClaims(token).getSubject());
        }catch(Exception e){
            return null;
        }
    }
}
