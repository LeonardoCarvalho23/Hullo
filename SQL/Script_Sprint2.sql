CREATE DATABASE  IF NOT EXISTS hullo;
USE hullo;

DROP TABLE IF EXISTS estado;
DROP TABLE IF EXISTS cidade;
DROP TABLE IF EXISTS aluno;
DROP TABLE IF EXISTS modulo;
DROP TABLE IF EXISTS aula;
DROP TABLE IF EXISTS professor;
DROP TABLE IF EXISTS aula_realizada;
DROP TABLE IF EXISTS log_professor;

CREATE TABLE estado(
 id_estado int NOT NULL AUTO_INCREMENT,
 nm_estado varchar(20) NOT NULL,
 sg_estado varchar(3) NOT NULL,
 CONSTRAINT PK_estado
 PRIMARY KEY (id_estado)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE cidade(
 id_cidade SMALLINT(5) NOT NULL AUTO_INCREMENT,
 nm_cidade varchar(50) NOT NULL,
 id_estado_cidade int NOT NULL,
 CONSTRAINT PK_cidade
 PRIMARY KEY (id_cidade),
 CONSTRAINT FK_cidade_estado
 FOREIGN KEY (id_estado_cidade)
 REFERENCES estado(id_estado)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE aluno (
  id_aluno int NOT NULL AUTO_INCREMENT,
  nome_aluno varchar(45) NOT NULL,
  sobrenome_aluno varchar(45) NOT NULL,
  cpf_aluno varchar(14) NOT NULL,
  email_aluno varchar(45) NOT NULL,
  senha_aluno varchar(40) NOT NULL,
  sexo_aluno varchar(1) NOT NULL,  -- M ou F
  data_nascimento_aluno date NOT NULL,
  telefone_aluno varchar(20) NOT NULL,
  profissao_aluno varchar(40) DEFAULT NULL,
  ativo_aluno char(1) DEFAULT 1, -- 0 = inativo ou 1 = ativo
  dt_insert_aluno datetime NOT NULL,
  dt_last_update_aluno datetime NOT NULL,
  cd_cidade_aluno SMALLINT(5) NOT NULL,
  CONSTRAINT PK_aluno
  PRIMARY KEY (id_aluno),
  CONSTRAINT FK_aluno_cidade
  FOREIGN KEY (cd_cidade_aluno)
  REFERENCES cidade(id_cidade)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE professor (
  id_professor int NOT NULL AUTO_INCREMENT,
  nome_professor varchar(45) NOT NULL,
  sobrenome_professor varchar(45) NOT NULL,
  cpf_professor varchar(14) NOT NULL,
  cnpj_professor varchar(18) DEFAULT NULL,
  email_professor varchar(45) NOT NULL,
  senha_professor varchar(40) NOT NULL,
  sexo_professor varchar(1) NOT NULL,  -- M ou F
  data_nascimento_professor date NOT NULL,
  telefone_professor varchar(20) NOT NULL,
  profissao_professor varchar(40) DEFAULT NULL,
  ativo_professor char(1) DEFAULT 1, -- 0 = inativo ou 1 = ativo
  dt_insert_professor datetime NOT NULL,
  dt_last_update_professor datetime NOT NULL,
  cd_cidade_professor SMALLINT(5) NOT NULL,
  CONSTRAINT PK_professor
  PRIMARY KEY (id_professor),
  CONSTRAINT FK_professor_cidade
  FOREIGN KEY (cd_cidade_professor)
  REFERENCES cidade(id_cidade)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE modulo(
 id_modulo int NOT NULL AUTO_INCREMENT,
 nm_modulo varchar(20) NOT NULL,
 indice_modulo float(3,2) NOT NULL, -- para definir ordem dos modulos
 ativo_modulo char (1) DEFAULT 1, -- 0 = inativo ou 1 = ativo
 dt_insert_modulo datetime NOT NULL,
 dt_last_update_modulo datetime NOT NULL,
 CONSTRAINT PK_estado
 PRIMARY KEY (id_modulo)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE aula(
 id_aula int NOT NULL AUTO_INCREMENT,
 nm_aula varchar(20) NOT NULL,
 numero_aula int NOT NULL, -- para definir posição da aula 1, 2, 3, 4 ou 5
 indice_aula varchar(1) NOT NULL, -- para definir o indice da aula A, B, C...
 revisao_aula varchar(300) NOT NULL,
 conteudo_aula varchar(600) NOT NULL,
 atividade_aula varchar(300) DEFAULT NULL,
 teaser_aula varchar(200) DEFAULT NULL,
 ativo_aula char (1) DEFAULT 1, -- 0 = inativo ou 1 = ativo
 dt_insert_aula datetime NOT NULL,
 dt_last_update_aula datetime NOT NULL,
 id_modulo_aula int NOT NULL,
 CONSTRAINT PK_aula
 PRIMARY KEY (id_aula),
 CONSTRAINT FK_aula_modulo
  FOREIGN KEY (id_modulo_aula)
  REFERENCES modulo(id_modulo)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE aula_realizada(
 id_aula_realizada int NOT NULL AUTO_INCREMENT,
 nota_model_aula_realizada int DEFAULT NULL,
 nota_practice_aula_realizada int DEFAULT NULL,
 nota_production_aula_realizada int DEFAULT NULL,
 status_ligacao_aula_realizada char (1) DEFAULT NULL, -- 1 = ligacao atendida por aluno, 0 ligacao não atendia
 dt_criacao_aula_realizada datetime NOT NULL,
 dt_inicio_aula_realizada datetime DEFAULT NULL,
 dt_fim_aula_realizada datetime DEFAULT NULL,
 id_aula_aula_aula_realizada int NOT NULL,
 id_aluno_aula_realizada int NOT NULL,
 id_professor_aula_realizada int DEFAULT NULL,
 CONSTRAINT PK_aula_realizada
  PRIMARY KEY (id_aula_realizada),
 CONSTRAINT FK_aula_realizada_aula
  FOREIGN KEY (id_aula_aula_aula_realizada)
  REFERENCES aula(id_aula),
 CONSTRAINT FK_aula_realizada_aluno
  FOREIGN KEY (id_aluno_aula_realizada)
  REFERENCES aluno(id_aluno),
 CONSTRAINT FK_aula_realizada_professor
  FOREIGN KEY (id_professor_aula_realizada)
  REFERENCES professor(id_professor)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE log_usuario(
 id_log_usuario int NOT NULL AUTO_INCREMENT,
 id_professor_log int DEFAULT NULL,
 id_aluno_log int DEFAULT NULL,
 dt_login datetime DEFAULT NULL,
 dt_logout datetime DEFAULT NULL,
 CONSTRAINT PK_log_usuario
 PRIMARY KEY (id_log_usuario)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
