package com.example.demo.repository;

import com.example.demo.model.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
    Optional<Curso> findByNombre(String nombre);
    Optional<Curso> findByNombreAndDescripcion(String nombre,String descripcion);
    // También hereda los métodos CRUD automáticamente
}
