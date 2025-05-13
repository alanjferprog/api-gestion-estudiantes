package com.example.demo.repository;

import com.example.demo.model.Estudiante;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByEmail(String email);
    // ¡No hace falta escribir nada! Hereda los métodos básicos: findAll, findById, save, delete, etc.
}
