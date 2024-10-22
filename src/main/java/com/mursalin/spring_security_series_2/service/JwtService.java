package com.mursalin.spring_security_series_2.service;

import com.mursalin.spring_security_series_2.model.Users;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtService {
    public String getToken(Users user) {
        Map<String, Object> claim = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+60*60*30))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            String sk = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            byte[] keyByte = Decoders.BASE64.decode(sk);
            return Keys.hmacShaKeyFor(keyByte);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
