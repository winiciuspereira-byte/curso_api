package com.curso_api.controller;

import com.curso_api.dto.CursoRequestDTO;
import com.curso_api.dto.CursoResponseDTO;
import com.curso_api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // GET /cursos
    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }

    // GET /cursos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(cursoService.buscarPorId(id));
    }

    // POST /cursos
    @PostMapping
    public ResponseEntity<CursoResponseDTO> criar(@Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.criar(dto));
    }

    // PUT /cursos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(cursoService.atualizar(id, dto));
    }

    // DELETE /cursos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
