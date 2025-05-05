package com.vileladev.enderecador.model.enums;

public enum EstadoSigla {
    AC("Acre"),
    AL("Alagoas"),
    AP("Amapá"),
    AM("Amazonas"),
    BA("Bahia"),
    CE("Ceará"),
    DF("Distrito Federal"),
    ES("Espírito Santo"),
    GO("Goiás"),
    MA("Maranhão"),
    MT("Mato Grosso"),
    MS("Mato Grosso do Sul"),
    MG("Minas Gerais"),
    PA("Pará"),
    PB("Paraíba"),
    PR("Paraná"),
    PE("Pernambuco"),
    PI("Piauí"),
    RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"),
    RS("Rio Grande do Sul"),
    RO("Rondônia"),
    RR("Roraima"),
    SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"),
    TO("Tocantins");

    private final String nome;

    EstadoSigla(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public static EstadoSigla fromSigla(String sigla) {
        try {
            return EstadoSigla.valueOf(sigla.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Sigla de estado inválida: " + sigla);
        }
    }
} 