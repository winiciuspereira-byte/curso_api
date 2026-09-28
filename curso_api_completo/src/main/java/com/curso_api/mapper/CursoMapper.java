package com.curso_api.mapper;

import com.curso_api.dto.CursoRequestDTO;
import com.curso_api.dto.CursoResponseDTO;
import com.curso_api.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    @Mapping(target = "instrutorId",   source = "instrutor.id")
    @Mapping(target = "instrutorNome", source = "instrutor.nome")
    CursoResponseDTO toResponseDTO(Curso curso);

    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "instrutor", ignore = true) // resolvido no CursoService
    Curso toEntity(CursoRequestDTO dto);

    @Mapping(target = "id",        ignore = true)
    @Mapping(target = "instrutor", ignore = true) // resolvido no CursoService
    void updateEntity(CursoRequestDTO dto, @MappingTarget Curso curso);
}
