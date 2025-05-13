package com.example.demo.service;

import com.example.demo.dto.CursoRequest;
import com.example.demo.dto.CursoResponse;
import com.example.demo.model.Curso;
import java.util.List;

public interface CursoService {
    CursoResponse crearCurso(CursoRequest request);
    CursoResponse actualizarCurso(Integer id, CursoRequest request);
    void eliminarCurso(Integer id);
    CursoResponse buscarPorIdCurso(Integer id);
    List<CursoResponse> listarTodosCursos();
}
