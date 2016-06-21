-- Data: 26/05/16 12:59:31
-- Autor: Willian Aparecido Carvalho
-- Projeto: Hospital - SENAC 1/2016


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `projetohospital` ;

CREATE SCHEMA IF NOT EXISTS `projetohospital` DEFAULT CHARACTER SET utf8 ;
USE `projetohospital` ;


DROP TABLE IF EXISTS `projetohospital`.`convenio` ;
DROP TABLE IF EXISTS `projetohospital`.`cliente` ;
DROP TABLE IF EXISTS `projetohospital`.`especialidade` ;
DROP TABLE IF EXISTS `projetohospital`.`unidade` ;
DROP TABLE IF EXISTS `projetohospital`.`medico` ;
DROP TABLE IF EXISTS `projetohospital`.`status_agendamento` ;
DROP TABLE IF EXISTS `projetohospital`.`agendamento` ;



CREATE TABLE IF NOT EXISTS `projetohospital`.`convenio` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `nome` VARCHAR(30) NULL DEFAULT NULL,
  `ativo` VARCHAR(1) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`cliente` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NULL DEFAULT NULL,
  `telefone` INT(11) NULL DEFAULT NULL,
  `celular` INT(11) NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `convenio_codigo` INT(10) UNSIGNED NOT NULL,
  `ativo` VARCHAR(1) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  `cpf` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_cliente_convenio_idx` (`convenio_codigo` ASC),
  CONSTRAINT `fk_cliente_convenio`
    FOREIGN KEY (`convenio_codigo`)
    REFERENCES `projetohospital`.`convenio` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`especialidade` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `nome` VARCHAR(30) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`unidade` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `nome` VARCHAR(30) NULL DEFAULT NULL,
  `endereco` VARCHAR(30) NULL DEFAULT NULL,
  `bairro` VARCHAR(20) NULL DEFAULT NULL,
  `cidade` VARCHAR(25) NULL DEFAULT NULL,
  `estado` VARCHAR(2) NULL DEFAULT NULL,
  `cep` VARCHAR(8) NULL DEFAULT NULL,
  `telefone` INT(11) NULL DEFAULT NULL,
  `ativo` VARCHAR(1) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`medico` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `nome` VARCHAR(30) NULL DEFAULT NULL,
  `crm` VARCHAR(15) NULL DEFAULT NULL,
  `cpf` VARCHAR(11) NULL DEFAULT NULL,
  `bloqueado` VARCHAR(1) NULL DEFAULT NULL,
  `especialidade_codigo` INT(10) UNSIGNED NOT NULL,
  `unidade_codigo` INT(10) UNSIGNED NOT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_medico_especialidade1_idx` (`especialidade_codigo` ASC),
  INDEX `fk_medico_unidade1_idx` (`unidade_codigo` ASC),
  CONSTRAINT `fk_medico_especialidade1`
    FOREIGN KEY (`especialidade_codigo`)
    REFERENCES `projetohospital`.`especialidade` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_medico_unidade1`
    FOREIGN KEY (`unidade_codigo`)
    REFERENCES `projetohospital`.`unidade` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`status_agendamento` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL,
  `descricao` VARCHAR(25) NULL DEFAULT NULL,
  `ativo` VARCHAR(1) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`agendamento` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `dt_hr_inicio` DATETIME NULL DEFAULT NULL,
  `reservado` VARCHAR(1) NULL DEFAULT NULL,
  `espera` VARCHAR(1) NULL DEFAULT NULL,
  `status_agendamento_codigo` INT(10) UNSIGNED NOT NULL,
  `medico_codigo` INT(10) UNSIGNED NOT NULL,
  `cliente_codigo` INT(10) UNSIGNED NOT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_agendamento_status_agendamento1_idx` (`status_agendamento_codigo` ASC),
  INDEX `fk_agendamento_medico1_idx` (`medico_codigo` ASC),
  INDEX `fk_agendamento_cliente1_idx` (`cliente_codigo` ASC),
  CONSTRAINT `fk_agendamento_cliente1`
    FOREIGN KEY (`cliente_codigo`)
    REFERENCES `projetohospital`.`cliente` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_medico1`
    FOREIGN KEY (`medico_codigo`)
    REFERENCES `projetohospital`.`medico` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_agendamento_status_agendamento1`
    FOREIGN KEY (`status_agendamento_codigo`)
    REFERENCES `projetohospital`.`status_agendamento` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

--VALORES TABELA CLIENTE
INSERT INTO `projetohospital`.`cliente` (`codigo`,`nome`,`telefone`,`celular`,`cadastro`,`convenio_codigo`,`ativo`,`deletado`,`cpf`)
    VALUES (00016,`Pedro Henrique`,1158481315,11941236985,date(`2016-05-02`),2,`S`,NULL,44122369874);
INSERT INTO `projetohospital`.`cliente` (`codigo`,`nome`,`telefone`,`celular`,`cadastro`,`convenio_codigo`,`ativo`,`deletado`,`cpf`)
    VALUES (00017,`Joao Paulo`,1147891254,11947123651,date(`2016-04-08`),1,`S`,NULL,11233653598);
INSERT INTO `projetohospital`.`cliente` (`codigo`,`nome`,`telefone`,`celular`,`cadastro`,`convenio_codigo`,`ativo`,`deletado`,`cpf`)
    VALUES (00018,`Kleber Bambam`,1121457458,11914745362,date(`2016-04-010`),4,`S`,NULL,11477853395);

--VALORES TABELA CONVENIO
INSERT INTO `projetohospital`.`convenio` (`codigo`,`nome`,`ativo`,`deletado`)
    VALUES (00001,`Allianz`,NULL,NULL);
INSERT INTO `projetohospital`.`convenio` (`codigo`,`nome`,`ativo`,`deletado`)
    VALUES (00002,`Ameplan`,NULL,NULL);
INSERT INTO `projetohospital`.`convenio` (`codigo`,`nome`,`ativo`,`deletado`)
    VALUES (00003,`Amico`,NULL,NULL);

--VALORES TABELA ESPECIALIDADE
INSERT INTO `projetohospital`.`especialidade` (`codigo`,`nome`,`deletado`)
    VALUES (00001,`Cardiologia`,NULL);
INSERT INTO `projetohospital`.`especialidade` (`codigo`,`nome`,`deletado`)
    VALUES (00002,`Cirurgia Geral`,NULL);
INSERT INTO `projetohospital`.`especialidade` (`codigo`,`nome`,`deletado`)
    VALUES (00003,`Fisioterapia`,NULL);

--VALORES TABELA UNIDADE
INSERT INTO `projetohospital`.`unidade` (`codigo`,`nome`,`endereco`,`bairro`,`cidade`,`estado`,`cep`,`telefone`,`ativo`,`deletado`)
    VALUES (000001,`Santana`,`Rua Voluntarios da Patria,655`,`Santana`,`Sao Paulo`,`SP`,04796425,1147893614,`S`,NULL);
INSERT INTO `projetohospital`.`unidade` (`codigo`,`nome`,`endereco`,`bairro`,`cidade`,`estado`,`cep`,`telefone`,`ativo`,`deletado`)
    VALUES (000002,`Interlagos`,`Rua Alesio Venturi,1255`,`Jd. Primavera`,`Sao Paulo`,`SP`,04785423,1147895478,`S`,NULL);
INSERT INTO `projetohospital`.`unidade` (`codigo`,`nome`,`endereco`,`bairro`,`cidade`,`estado`,`cep`,`telefone`,`ativo`,`deletado`)
    VALUES (000003,`Jundiai`,`Rua Demostenes Brito,43`,`Jd. Europa`,`Jundiai`,`SP`,04896510,1154781235,`S`,NULL);

--VALORES TABELA MEDICO
INSERT INTO `projetohospital`.`medico` (`codigo`,`nome`,`crm`,`cpf`,`bloqueado`,`especialidade_codigo`,`unidade_codigo`,`deletado`)
    VALUES (00001,`Joao dos Santos`,12345678,44785233697,NULL,1,1,NULL);
INSERT INTO `projetohospital`.`medico` (`codigo`,`nome`,`crm`,`cpf`,`bloqueado`,`especialidade_codigo`,`unidade_codigo`,`deletado`)
    VALUES (00002,`Maria da Silva`,87654321,44102366985,NULL,2,2,NULL);
INSERT INTO `projetohospital`.`medico` (`codigo`,`nome`,`crm`,`cpf`,`bloqueado`,`especialidade_codigo`,`unidade_codigo`,`deletado`)
    VALUES (00003,`Valdarmar Soares`,45632178,55236877410,NULL,3,3,NULL);

--VALORES TABELA STATUS_AGENDAMENTO
INSERT INTO `projetohospital`.`status_agendameto` (`codigo`,`descricao`,`ativo`,`deletado`)
    VALUES (00001,`Agendado`,`S`,NULL);
INSERT INTO `projetohospital`.`status_agendameto` (`codigo`,`descricao`,`ativo`,`deletado`)
    VALUES (00002,`Em Espera`,`S`,NULL);
INSERT INTO `projetohospital`.`status_agendameto` (`codigo`,`descricao`,`ativo`,`deletado`)
    VALUES (00003,`Transf. Data`,`S`,NULL);

--VALORES TABELA AGENDAMENTO
INSERT INTO `projetohospital`.`agendamento` (`codigo`,`dt_hr_inicio`,`reservado`,`espera`,`status_agendamento_codigo`,`medico_codigo`,`cliente_codigo`,`deletado`)
    VALUES (0000000002,datetime(`2016-06-06 14:00:00`),`S` ,`N`,1,1,16,NULL);
INSERT INTO `projetohospital`.`agendamento` (`codigo`,`dt_hr_inicio`,`reservado`,`espera`,`status_agendamento_codigo`,`medico_codigo`,`cliente_codigo`,`deletado`)
    VALUES (0000000003,datetime(`2016-06-06 16:00:00`),`S` ,`N`,1,2,17,NULL);
INSERT INTO `projetohospital`.`agendamento` (`codigo`,`dt_hr_inicio`,`reservado`,`espera`,`status_agendamento_codigo`,`medico_codigo`,`cliente_codigo`,`deletado`)
    VALUES (0000000005,datetime(`2016-06-11 08:00:00`),`S` ,`N`,1,3,18,NULL);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
