package com.vileladev.enderecador.service.impl;

import com.vileladev.enderecador.model.Endereco;
import com.vileladev.enderecador.model.dto.EnderecoDTO;
import com.vileladev.enderecador.repository.EnderecoRepository;
import com.vileladev.enderecador.service.EnderecoService;
import com.vileladev.enderecador.utils.mapper.EnderecoMapper;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoMapper enderecoMapper;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository, EnderecoMapper enderecoMapper) {
        this.enderecoRepository = enderecoRepository;
        this.enderecoMapper = enderecoMapper;
    }

    @Override
    public EnderecoDTO salvar(EnderecoDTO enderecoDTO) {
        var result = enderecoRepository.save(enderecoMapper.toEntity(enderecoDTO));
        return enderecoMapper.toDTO(result);
    }


    @Override
    public EnderecoDTO atualizar(Long id, EnderecoDTO enderecoDTO) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntityNotFoundException("Endereço não encontrado com o ID: " + id);
        }
        Endereco endereco = enderecoMapper.toEntity(enderecoDTO);
        endereco.setId(id);
        Endereco updatedEndereco = enderecoRepository.save(endereco);
        return enderecoMapper.toDTO(updatedEndereco);
    }

    @Override
    public EnderecoDTO buscarPorId(Long id) {
        return enderecoMapper.toDTO(enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado com o ID: " + id)));
    }

    @Override
    public void deletar(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EntityNotFoundException("Endereço não encontrado com o ID: " + id);
        }
        enderecoRepository.deleteById(id);
    }

    @Override
    public List<EnderecoDTO> listarTodos() {
        return enderecoRepository.findAll().stream()
                .map(enderecoMapper::toDTO)
                .collect(Collectors.toList());
    }
} 