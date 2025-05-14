package com.example.demo.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println("🔍 Filtro interceptando: " + path); // <- Agregado

        //Evitar que Swagger pase por el filtro JWT
        if (path.startsWith("/v3/api-docs") ||
                path.startsWith("/swagger-ui") ||
                path.startsWith("/swagger-resources") ||
                path.startsWith("/webjars") ||
                path.equals("/swagger-ui.html")) {

            System.out.println("🚫 Ruta ignorada por JWT Filter: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remueve "Bearer "

            try {
                // Validar token
                Claims claims = jwtUtil.getClaims(token);
                String username = claims.getSubject();

                // Recuperar el rol desde el token
                String role = claims.get("role", String.class);

                // Crear autenticación
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role))
                        );

                // Establecer autenticación en el contexto de Spring
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {
                // Si falla la validación, no se hace nada y sigue sin autenticar
                System.out.println("Token inválido o expirado: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response); // Continua con la cadena
    }
}
