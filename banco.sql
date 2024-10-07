CREATE DATABASE IF NOT EXISTS 'SystemBarber';

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(100)
);

INSERT INTO users (email, senha) VALUES ("teste", aes_encrypt("abc123","key"));

UPDATE users SET email = "teste@gmail.com" WHERE users.email LIKE '%teste@gmail.com%';

SELECT * FROM Users;

DELETE FROM users WHERE users.email LIKE '%teste@gmail.com%';
