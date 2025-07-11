-- CLIENTE
INSERT INTO cliente (id_cliente, nome, documento)
VALUES (3, 'Julia Batista', 12345678901);
INSERT INTO cliente (id_cliente, nome, documento)
VALUES (2, 'João da Silva', 47695238214);


-- PEDIDO
INSERT INTO pedido (id_pedido, code, id_cliente, status, data)
VALUES (4, 1004, 1, 'processed', now());
INSERT INTO pedido (id_pedido, code, id_cliente, status, data)
VALUES (2, 1002, 2, 'processing', now());
INSERT INTO pedido (id_pedido, code, id_cliente, status, data)
VALUES (3, 1003, 2, 'new', now());

-- ITENS
INSERT INTO item (quantidade, nome, id_cliente, id_pedido, preco)
VALUES
  (100, 'lápis', 1, 1, 1.10),
  (10, 'caderno', 1, 1, 1.00);
INSERT INTO item (quantidade, nome, id_cliente, id_pedido, preco)
VALUES
(5, 'mochila', 2, 2, 55.20),
(2, 'notebook', 2, 2, 2256.99);
INSERT INTO item (quantidade, nome, id_cliente, id_pedido, preco)
VALUES
(2, 'livro', 2, 3, 25),
(1, 'caneta', 2, 3, 5.20);