
/***
* Scripts para criacao e insersao de dados
* Base Dados Biblioteca
* Casssio Trindade
* 03/2017
***/

CREATE SCHEMA biblioteca_e ;
USE ages_e;


-- DROP TABLE TB_USUARIO;


-- Tabela Usuario
CREATE TABLE tb_usuario (
  ID_USUARIO int(11) NOT NULL AUTO_INCREMENT,
  SENHA varchar(45) NOT NULL,
  EMAIL varchar(120) DEFAULT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL,
  PRIMARY KEY (ID_USUARIO),
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

