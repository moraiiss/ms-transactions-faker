CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT(20) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    payer BIGINT(20) UNSIGNED,
    payee BIGINT(20) UNSIGNED,
    value DECIMAL(10,2),
    created_at TIMESTAMP,

    FOREIGN KEY (payer) REFERENCES wallets (id),
    FOREIGN KEY (payee) REFERENCES wallets (id)
);