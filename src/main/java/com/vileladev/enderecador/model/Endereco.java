package com.vileladev.enderecador.model;

import com.vileladev.enderecador.model.enums.EstadoSigla;
import com.vileladev.enderecador.model.enums.PaisSigla;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "enderecos")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String endereco;
    private String cep;
    
    @Column(name = "tipo_logradouro")
    private String tipoLogradouro;
    
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_sigla")
    private EstadoSigla estadoSigla;
    
    private String pais;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "pais_sigla")
    private PaisSigla paisSigla;
} 