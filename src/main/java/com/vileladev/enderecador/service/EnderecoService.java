package com.vileladev.enderecador.service;

import com.vileladev.enderecador.model.Endereco;
import com.vileladev.enderecador.model.dto.EnderecoDTO;
import java.util.List;

public interface EnderecoService {
    EnderecoDTO salvar(EnderecoDTO enderecoDTO);
    EnderecoDTO atualizar(Long id, EnderecoDTO enderecoDTO);
    EnderecoDTO buscarPorId(Long id);
    void deletar(Long id);
    List<EnderecoDTO> listarTodos();
} 