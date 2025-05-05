CREATE TABLE IF NOT EXISTS enderecos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    endereco VARCHAR(255) NOT NULL COMMENT 'Endereço completo',
    cep VARCHAR(9) NOT NULL COMMENT 'CEP no formato 12345-678',
    tipo_logradouro VARCHAR(50) COMMENT 'Tipo do logradouro (Rua, Avenida, etc)',
    logradouro VARCHAR(255) NOT NULL COMMENT 'Nome do logradouro',
    bairro VARCHAR(100) NOT NULL COMMENT 'Nome do bairro',
    cidade VARCHAR(100) NOT NULL COMMENT 'Nome da cidade',
    estado VARCHAR(100) NOT NULL COMMENT 'Nome do estado',
    estado_sigla VARCHAR(2) NOT NULL COMMENT 'Sigla do estado (UF)',
    pais VARCHAR(100) NOT NULL COMMENT 'Nome do país',
    pais_sigla VARCHAR(2) NOT NULL COMMENT 'Sigla do país',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização do registro',
    CONSTRAINT uk_endereco_cep UNIQUE (endereco, cep),
    INDEX idx_cep (cep),
    INDEX idx_cidade (cidade),
    INDEX idx_estado (estado)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Tabela de endereços'; 