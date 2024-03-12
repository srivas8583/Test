package com.example.demo.Utility;

import org.springframework.stereotype.Component;

import com.example.demo.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "yourSecretKey"; // Replace with a secure key

    public static String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("type", user.getType())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static String getUserTypeFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get("type", String.class);
    }
}
