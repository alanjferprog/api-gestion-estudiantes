package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "mi_clave_secreta_super_segura_123456";

    // Genera un token válido por 1 hora
    public String generarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // ✅ usar .getBytes()
                .compact();
    }

    // Obtiene el username (subject) del token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()) // ✅ usar .getBytes()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Obtiene todos los claims del token
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY.getBytes()) // ✅ usar .getBytes()
                .parseClaimsJws(token)
                .getBody();
    }
}
