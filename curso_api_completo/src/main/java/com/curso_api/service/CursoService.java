package com.curso_api.service;

import com.curso_api.dto.CursoRequestDTO;
import com.curso_api.dto.CursoResponseDTO;
import com.curso_api.entity.Curso;
import com.curso_api.entity.Instrutor;
import com.curso_api.mapper.CursoMapper;
import com.curso_api.repository.CursoRepository;
import com.curso_api.repository.InstrutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;
    private final InstrutorRepository instrutorRepository;

    public CursoService(CursoRepository cursoRepository,
                        CursoMapper cursoMapper,
                        InstrutorRepository instrutorRepository) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
        this.instrutorRepository = instrutorRepository;
    }

    public List<CursoResponseDTO> listarTodos() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toResponseDTO)
                .toList();
    }

    public CursoResponseDTO buscarPorId(Long id) {
        return cursoMapper.toResponseDTO(buscarOuLancar(id));
    }

    public CursoResponseDTO criar(CursoRequestDTO dto) {
        Curso curso = cursoMapper.toEntity(dto);
        resolverInstrutor(curso, dto.instrutorId());
        return cursoMapper.toResponseDTO(cursoRepository.save(curso));
    }

    public CursoResponseDTO atualizar(Long id, CursoRequestDTO dto) {
        Curso curso = buscarOuLancar(id);
        cursoMapper.updateEntity(dto, curso);
        resolverInstrutor(curso, dto.instrutorId());
        return cursoMapper.toResponseDTO(cursoRepository.save(curso));
    }

    public void deletar(Long id) {
        buscarOuLancar(id);
        cursoRepository.deleteById(id);
    }

    // -------------------------------------------------------------------------
    // Auxiliares privados
    // -------------------------------------------------------------------------

    private Curso buscarOuLancar(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com id: " + id));
    }

    private void resolverInstrutor(Curso curso, Long instrutorId) {
        if (instrutorId != null) {
            Instrutor instrutor = instrutorRepository.findById(instrutorId)
                    .orElseThrow(() -> new EntityNotFoundException("Instrutor não encontrado com id: " + instrutorId));
            curso.setInstrutor(instrutor);
        } else {
            curso.setInstrutor(null);
        }
    }
}
