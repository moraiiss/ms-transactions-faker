CREATE TABLE IF NOT EXISTS wallets (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    cpf BIGINT,
    email VARCHAR(100),
    password VARCHAR(100),
    type INT,
    balance DECIMAL(10,2)
);

CREATE TABLE IF NOT EXISTS transactions (
    id SERIAL PRIMARY KEY,
    payer INT,
    payee INT,
    value DECIMAL(10,2),
    created_at TIMESTAMP,

    FOREIGN KEY (payer) REFERENCES wallets (id),
    FOREIGN KEY (payee) REFERENCES wallets (id)
);

CREATE UNIQUE INDEX idx_cpf ON wallets (cpf);
CREATE UNIQUE INDEX idx_email ON wallets (email);