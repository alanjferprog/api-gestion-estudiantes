package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegistroRequest;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autorizaciones", description = "")
public class AuthController {

    private final UsuarioService usuarioService;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, JwtUtil jwtUtil, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Registro en la base de datos")
    @PostMapping("/register")
    public Usuario registrar(@RequestBody RegistroRequest request) {
        return usuarioService.registrarUsuario(request);
    }

    @Operation(summary = "Login en la base de datos")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(request.getUsername());

        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }

        Usuario usuario = optionalUsuario.get();

        // Verifica que la contraseña ingresada coincida con la encriptada
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        String token = jwtUtil.generarToken(usuario.getUsername());

        return ResponseEntity.ok(token);
    }

}


