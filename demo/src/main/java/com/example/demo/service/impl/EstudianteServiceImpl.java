package com.example.demo.service.impl;

import com.example.demo.dto.EstudianteRequest;
import com.example.demo.dto.EstudianteResponse;
import com.example.demo.exception.RecursoDuplicadoException;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.model.Estudiante;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public EstudianteResponse crearEstudiante(EstudianteRequest request) {
        Optional<Estudiante> existente = estudianteRepository.findByEmail(request.getEmail());
        if (existente.isPresent()) {
            throw new RecursoDuplicadoException("Ya existe un estudiante con el email: " + request.getEmail());
        }
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(request.getNombre());
        estudiante.setEmail(request.getEmail());
        Estudiante guardado = estudianteRepository.save(estudiante);
        return mapToResponse(guardado);
    }

    @Override
    public EstudianteResponse actualizar(Integer id, EstudianteRequest request) {
        Estudiante existente = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con el id: " + id));

        // Verifico si el nuevo email ya está siendo usado por otro estudiante
        Optional<Estudiante> existenteConEmail = estudianteRepository.findByEmail(request.getEmail());
        if (existenteConEmail.isPresent() && existenteConEmail.get().getId() != id) {
            throw new RecursoDuplicadoException("Ya existe un estudiante con el email: " + request.getEmail());
        }

        existente.setNombre(request.getNombre());
        existente.setEmail(request.getEmail());
        Estudiante actualizado= estudianteRepository.save(existente);

        return mapToResponse(actualizado);
    }

    @Override
    public void eliminar(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con el id: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    @Override
    public EstudianteResponse buscarPorId(Integer id) {
        Estudiante estudiante= estudianteRepository.findById(id).orElseThrow( () -> new RecursoNoEncontradoException("Estudiante con ID " + id + " no encontrado"));
        return mapToResponse(estudiante);
    }

    @Override
    public List<EstudianteResponse> listarTodos() {
        return estudianteRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EstudianteResponse mapToResponse(Estudiante estudiante) {
        return new EstudianteResponse(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getEmail()
        );
    }
}
