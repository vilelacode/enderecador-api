package com.vileladev.enderecador.model.dto;

import com.vileladev.enderecador.model.enums.EstadoSigla;
import com.vileladev.enderecador.model.enums.PaisSigla;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;
    private String endereco;
    private String cep;
    private String tipoLogradouro;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private EstadoSigla estadoSigla;
    private String pais;
    private PaisSigla paisSigla;
} 