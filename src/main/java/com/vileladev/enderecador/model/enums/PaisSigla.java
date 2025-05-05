package com.vileladev.enderecador.model.enums;

public enum PaisSigla {
    BR("Brasil"),
    US("Estados Unidos"),
    AR("Argentina"),
    CL("Chile"),
    CO("Colômbia"),
    PE("Peru"),
    UY("Uruguai"),
    PY("Paraguai"),
    BO("Bolívia"),
    VE("Venezuela"),
    EC("Equador"),
    GY("Guiana"),
    SR("Suriname"),
    GF("Guiana Francesa");

    private final String nome;

    PaisSigla(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static PaisSigla fromSigla(String sigla) {
        try {
            return PaisSigla.valueOf(sigla.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sigla de país inválida: " + sigla);
        }
    }
} 