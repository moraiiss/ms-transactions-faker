CREATE TABLE IF NOT EXISTS wallets (
    id BIGINT(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    cpf BIGINT,
    email VARCHAR(100),
    password VARCHAR(100),
    type INT,
    balance DECIMAL(10,2)
);