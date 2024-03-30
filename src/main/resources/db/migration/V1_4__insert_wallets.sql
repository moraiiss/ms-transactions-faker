DELETE FROM transactions;

DELETE FROM wallets;

INSERT INTO
    wallets (
    id, name, cpf, email, password, type, balance
)
VALUES (
           1, "Teste - Pagador", 12345612323, "junior@mail.com", "123123", 1, 1000.00
       );

INSERT INTO
    wallets (
    id, name, cpf, email, password, type, balance
)
VALUES (
           2, "Teste - Recebedor", 12345612333, "anna@mail.com", "123123", 2, 500.00
       );