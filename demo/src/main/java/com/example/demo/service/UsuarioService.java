package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.dto.RegistroRequest;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // Encriptador de contraseñas
    }

    public Usuario registrarUsuario(RegistroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // Encriptación
        usuario.setRole("ROLE_USER");
        return usuarioRepository.save(usuario);
    }
}

