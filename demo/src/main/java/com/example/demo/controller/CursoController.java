package com.example.demo.controller;

import com.example.demo.dto.CursoRequest;
import com.example.demo.dto.CursoResponse;
import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Cursos", description = "CRUD de cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Operation(summary = "Crear un curso")
    @PostMapping
    public ResponseEntity<CursoResponse> crearCurso(@Valid @RequestBody CursoRequest request) {
        return new ResponseEntity<>(cursoService.crearCurso(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los curso")
    @GetMapping
    public List<CursoResponse> listar() {
        return cursoService.listarTodosCursos();
    }

    @Operation(summary = "Obtener un curso")
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(cursoService.buscarPorIdCurso(id));
    }

    @Operation(summary = "Actualizar un curso")
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> actualizar(@PathVariable Integer id, @Valid @RequestBody CursoRequest request) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, request));
    }

    @Operation(summary = "Eliminar un curso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
