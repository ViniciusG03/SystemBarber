CREATE DATABASE IF NOT EXISTS 'SystemBarber';

CREATE TABLE IF NOT EXISTS 'Users' (
	´id´ int() NOT NULL PRIMARY AUTO_INCREMENT,
    ´email´ varchar(100) NOT NULL,
    ´password´ varchar NOT NULL(255),
); 

INSERT INTO users (email, senha) VALUES ("teste", aes_encrypt("abc123","key"));

UPDATE users SET email = "teste@gmail.com" WHERE users.email LIKE '%teste@gmail.com%';

SELECT * FROM Users;

DELETE FROM users WHERE users.email LIKE '%teste@gmail.com%';
