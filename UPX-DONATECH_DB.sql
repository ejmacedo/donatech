create database donatech_db;

use donatech_db;

CREATE TABLE doador (
    id_doador BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    rua VARCHAR(100),
    bairro VARCHAR(100),
    num_casa VARCHAR(20),
    num_telefone VARCHAR(20)
);

-------------------------------------------------------------------------------------

CREATE TABLE produto (
    id_produto BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(50),
    estado_produto VARCHAR(50),
    id_doador BIGINT,
    FOREIGN KEY (id_doador) REFERENCES doador(id_doador)
);

-------------------------------------------------------------------------------------

CREATE TABLE imagem (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(50),
    id_produto BIGINT,
    FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

-------------------------------------------------------------------------------------

-- CREATE DOADOR
INSERT INTO doador (nome, cpf, email, senha, rua, bairro, num_casa, num_telefone)
VALUES ('Eduardo José Macedo', '47588744589', 'eduardo@gmail.com', 'senha1234', 'Rua 1', 'Bairro 13', '132', '(11)99875-4475'),
('Bruno da Silva Leite', '65874458744', 'bruno@gmail.com', 'senha4321', 'Rua 2', 'Bairro 13', '243', '(11)98745-7845'),
('Gabriel Alberto de Oliveira', '12325498856', 'gabriel@gmail.com', 'senha8910', 'Rua 3', 'Bairro 14', '354', '(15)96588-1245'),
('Mateus Franco Bezerra', '98569984712', 'mateus@gmail.com', 'senha1098', 'Rua 4', 'Bairro 19', '12', '(35)98755-5566'),
('Pedro Ibraim Silveira Coan', '41985874855', 'pedro@gmail.com', 'senha2222', 'Rua 5', 'Bairro 122', '333', '(15)95615-6684'),
('Wellington Henrique', '65748451225', 'wellington@gmail.com', 'senha5425', 'Rua 6', 'Bairro 1', '522', '(22)93287-4913'),
('Augusto Luis', '62443429985', 'augusto@gmail.com', 'senha6657', 'Rua 7', 'Bairro 10', '2', '(55)92225-9875');

-------------------------------------------------------------------------------------

-- CREATE PRODUTO
INSERT INTO produto (nome, descricao, categoria, estado_produto, id_doador)
VALUES
  ('Memória RAM DDR4 16GB', 'Memória RAM para PC, 3200MHz', 'Informática', 'Funcionando', 1),
  ('Teclado Mecânico', 'Teclado mecânico RGB, switches Cherry MX Blue', 'Informática', 'Tecla wasd com defeito', 2),
  ('Monitor Gamer', 'Monitor 27 polegadas, 144Hz, resolução 2K', 'Informática', 'Não liga', 3),
  ('Notebook', 'Intel Core i3, 4GB RAM, 250GB SSD', 'Informática', 'Bom', 4),
  ('Mouse ', 'Mouse sem fio, 8000 DPI', 'Informática', 'Scroll com defeito', 5);

-------------------------------------------------------------------------------------

-- CREATE IMAGEM
INSERT INTO imagem (url, id_produto)
VALUES
  ('/imagens/produtos/memoria_ram.jpg', 1),
  ('/imagens/produtos/memoria_ram_2.jpg', 1),
  ('/imagens/produtos/teclado_mecanico.jpg', 2),
  ('/imagens/produtos/teclado_mecanico_detalhe.jpg', 2),
  ('/imagens/produtos/monitor_gamer.jpg', 3),
  ('/imagens/produtos/monitor_gamer_lateral.jpg', 3),
  ('/imagens/produtos/notebook.jpg', 4),
  ('/imagens/produtos/mouse.jpg', 5);

-------------------------------------------------------------------------------------

-- READ DOADOR
SELECT * FROM doador;
SELECT * FROM doador WHERE nome = 'Eduardo José Macedo';
SELECT COUNT(*) FROM doador;

-- UPDATE DOADOR
UPDATE doador SET nome = 'Novo Nome' WHERE id_doador = 1;
UPDATE doador SET num_telefone = '(11)98765-4321' WHERE id_doador = 2;

-- DELETE DOADOR
DELETE FROM doador WHERE id_doador = 1;
DELETE FROM doador WHERE bairro = 'Bairro 13';

-------------------------------------------------------------------------------------

-- READ PRODUTO
SELECT * FROM produto;
SELECT * FROM produto WHERE id_doador = 1;
SELECT * FROM produto WHERE categoria = 'Informática';

-- UPDATE PRODUTO
UPDATE produto SET descricao = 'Nova descrição' WHERE id_produto = 2;
UPDATE produto SET estado_produto = 'Doado' WHERE id_produto = 1;

-- DELETE PRODUTO
DELETE FROM produto WHERE id_produto = 2;
DELETE FROM produto WHERE estado_produto LIKE '%defeito%';

-------------------------------------------------------------------------------------

-- READ IMAGEM
SELECT * FROM imagem;
SELECT * FROM imagem WHERE id_produto = 2;

-- UPDATE IMAGEM
UPDATE imagem SET caminho = '/novas/imagens/produto1.jpg' WHERE id = 1;

-- DELETE IMAGEM
DELETE FROM imagem WHERE id_produto = 3;

-------------------------------------------------------------------------------------

-- READ - QUAIS PRODUTOS OS DOADORES DOARAM
SELECT 
    d.nome AS doador,
    p.nome AS produto,
    p.descricao
FROM 
    doador d
INNER JOIN produto p ON d.id_doador = p.id_doador;

-------------------------------------------------------------------------------------

-- READ - QUAIS IMAGENS SAO REFERENTES AOS PRODUTOS
SELECT 
    p.nome AS produto,
    i.url AS imagem
FROM 
    produto p
INNER JOIN imagem i ON p.id_produto = i.id_produto;


