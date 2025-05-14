package com.example.demo.controller;

import com.example.demo.dto.EstudianteRequest;
import com.example.demo.dto.EstudianteResponse;
import com.example.demo.model.Estudiante;
import com.example.demo.service.EstudianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@Tag(name = "Estudiantes", description = "CRUD de estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Operation(summary = "Crear un estudiante")
    @PostMapping
    public ResponseEntity<EstudianteResponse> crearEstudiante(@RequestBody @Valid EstudianteRequest request) {
        EstudianteResponse response = estudianteService.crearEstudiante(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Obtener todos los estudiantes")
    @GetMapping
    public ResponseEntity<List<EstudianteResponse>> listar() {
        List<EstudianteResponse> lista= estudianteService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Obtener un estudiante mediante")
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(estudianteService.buscarPorId(id));
    }

    @Operation(summary = "Actualizar un estudiante")
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponse> actualizar(@PathVariable Integer id, @Valid @RequestBody EstudianteRequest request) {
        return ResponseEntity.ok(estudianteService.actualizar(id, request));
    }

    @Operation(summary = "Eliminar un estudiante")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
