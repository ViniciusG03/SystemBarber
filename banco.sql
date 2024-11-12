CREATE DATABASE IF NOT EXISTS 'SystemBarber';

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS barbearia (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(15) NOT NULL,
    formas_pagamento VARCHAR(255),
    quantidade_funcionarios ENUM('1 a 2 Funcionarios', '1 a 4 Funcionarios', '1 a 6 ou +') NOT NULL,
    proprietario VARCHAR(100) NOT NULL,
    dias_servico ENUM('segunda a sexta', 'todos os dias') NOT NULL,
    localizacao VARCHAR(100) NOT NULL,
    horario_funcionamento VARCHAR(20) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS agendamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_barbearia INT NOT NULL,
    nome_cliente VARCHAR(100) NOT NULL,
    email_cliente VARCHAR(100) NOT NULL,
    telefone_cliente VARCHAR(15) NOT NULL,
    forma_pagamento ENUM('Cartão de Crédito', 'Boleto Bancário', 'Pix') NOT NULL,
    data_horario DATETIME NOT NULL,
    FOREIGN KEY (id_barbearia) REFERENCES barbearia(id) ON DELETE CASCADE
) ENGINE=InnoDB;
