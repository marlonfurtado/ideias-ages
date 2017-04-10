
/***
* Scripts para criacao e insersao de dados
* Base Dados Ideas AGES
* Casssio Trindade
* 04/2017
***/

CREATE SCHEMA ideas_ages;
USE ideias_ages;


-- DROP TABLE TB_USER;


-- Tabela Usuario
CREATE TABLE tb_user (
  ID_USER int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(120) DEFAULT NULL,
  EMAIL varchar(120) DEFAULT NULL,
  SENHA varchar(45) NOT NULL,
  PHONE varchar(45) NOT NULL,
  ROLE int(10) NOT NULL,
  STATUS varchar(2) NOT NULL,
  DATE_INSERT datetime DEFAULT NULL,
  PRIMARY KEY (ID_USER)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

