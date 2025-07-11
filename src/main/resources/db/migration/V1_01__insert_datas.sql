-- CLIENTE
INSERT INTO cliente (id_cliente, nome, documento)
VALUES (10, 'Julia Batista', 12345678901);
INSERT INTO cliente (id_cliente, nome, documento)
VALUES (20, 'João da Silva', 47695238214);


-- PEDIDO
INSERT INTO pedido (id_pedido, code, id_cliente, status, data)
VALUES (10, 1001, 10, 'processed', now());
INSERT INTO pedido (id_pedido, code, id_cliente, status, data)
VALUES (20, 1002, 20, 'processing', now());
INSERT INTO pedido (id_pedido, code, id_cliente, status, data)
VALUES (30, 1003, 20, 'new', now());

-- ITENS
INSERT INTO item (id_item, quantidade, nome, id_cliente, id_pedido, preco)
VALUES
  (20,100, 'lápis', 10, 10, 1.10),
  (22,10, 'caderno', 10, 10, 1.00);
INSERT INTO item (id_item, quantidade, nome, id_cliente, id_pedido, preco)
VALUES
(21,5, 'mochila', 20, 20, 55.20),
(23,2, 'notebook', 20, 20, 2256.99);
INSERT INTO item (id_item, quantidade, nome, id_cliente, id_pedido, preco)
VALUES
(24,2, 'livro', 20, 30, 25),
(25,1, 'caneta', 20, 30, 5.20);