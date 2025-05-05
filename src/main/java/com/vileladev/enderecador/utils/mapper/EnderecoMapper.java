package com.vileladev.enderecador.utils.mapper;

import com.vileladev.enderecador.model.Endereco;
import com.vileladev.enderecador.model.dto.EnderecoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {


    @Mapping(target = "id", ignore = true)
    Endereco toEntity(EnderecoDTO dto);
    
    EnderecoDTO toDTO(Endereco entity);
    
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(EnderecoDTO dto, @MappingTarget Endereco entity);
} 