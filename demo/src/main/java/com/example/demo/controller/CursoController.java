package com.example.demo.controller;

import com.example.demo.dto.CursoRequest;
import com.example.demo.dto.CursoResponse;
import com.example.demo.model.Curso;
import com.example.demo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<CursoResponse> crearCurso(@Valid @RequestBody CursoRequest request) {
        return new ResponseEntity<>(cursoService.crearCurso(request), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CursoResponse> listar() {
        return cursoService.listarTodosCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponse> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(cursoService.buscarPorIdCurso(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponse> actualizar(@PathVariable Integer id, @Valid @RequestBody CursoRequest request) {
        return ResponseEntity.ok(cursoService.actualizarCurso(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cursoService.eliminarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
