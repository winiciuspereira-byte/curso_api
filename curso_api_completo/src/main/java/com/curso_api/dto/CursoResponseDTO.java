package com.curso_api.dto;

public record CursoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Integer cargaHoraria,
        Long instrutorId,
        String instrutorNome
) {}
