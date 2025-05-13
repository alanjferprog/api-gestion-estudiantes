package com.example.demo.service;

import com.example.demo.dto.EstudianteRequest;
import com.example.demo.model.Estudiante;
import com.example.demo.dto.EstudianteResponse;

import java.util.List;

public interface EstudianteService {

    EstudianteResponse crearEstudiante(EstudianteRequest request);
    EstudianteResponse actualizar(Integer id, EstudianteRequest request);
    void eliminar(Integer id);
    EstudianteResponse buscarPorId(Integer id);
    List<EstudianteResponse> listarTodos();
}
