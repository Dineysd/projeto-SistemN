CREATE TABLE categoria(
 id_categoria BIGINT(20) primary key AUTO_INCREMENT,
 nome varchar(50) not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria(nome)values('lazer');
INSERT INTO categoria(nome)values('supermercado');
INSERT INTO categoria(nome)values('alimentação');
INSERT INTO categoria(nome)values('farmacia');
INSERT INTO categoria(nome)values('outros');