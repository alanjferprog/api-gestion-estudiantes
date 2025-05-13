package com.example.demo.service.impl;

import com.example.demo.dto.CursoRequest;
import com.example.demo.dto.CursoResponse;
import com.example.demo.dto.EstudianteResponse;
import com.example.demo.exception.RecursoDuplicadoException;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.model.Curso;
import com.example.demo.model.Estudiante;
import com.example.demo.repository.CursoRepository;
import com.example.demo.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public CursoResponse crearCurso(CursoRequest request) {
        Optional<Curso> existente = cursoRepository.findByNombreAndDescripcion(request.getNombre(),request.getDescripcion());
        if (existente.isPresent()) {
            throw new RecursoDuplicadoException("Ya existe un curso con el nombre: " + request.getNombre() + " y descripcion: "+ request.getDescripcion());
        }
        Curso curso = new Curso();
        curso.setNombre(request.getNombre());
        curso.setDescripcion(request.getDescripcion());
        Curso guardado = cursoRepository.save(curso);

        return mapToResponse(guardado);
    }

    @Override
    public CursoResponse actualizarCurso(Integer id, CursoRequest request) {
        Curso existente = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con el id: " + id));

        existente.setNombre(request.getNombre());
        existente.setDescripcion(request.getDescripcion());
        Curso actualizado = cursoRepository.save(existente);

        return mapToResponse(actualizado);
    }

    @Override
    public void eliminarCurso(Integer id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con el id: " + id);
        }
        cursoRepository.deleteById(id);
    }

    @Override
    public CursoResponse buscarPorIdCurso(Integer id) {
        Curso curso= cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso con ID " + id + " no encontrado"));
        return mapToResponse(curso);
    }

    @Override
    public List<CursoResponse> listarTodosCursos() {
        return cursoRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private CursoResponse mapToResponse(Curso request) {
        return new CursoResponse(
                request.getId(),
                request.getNombre(),
                request.getDescripcion()
        );
    }
}
