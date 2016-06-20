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
  `telefone` INT(11) NULL DEFAULT NULL,
  `responsavel` VARCHAR(25) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `ativo` VARCHAR(1) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `projetohospital`.`cliente` (
  `codigo` INT(6) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(30) NULL DEFAULT NULL,
  `endereco` VARCHAR(30) NULL DEFAULT NULL,
  `bairro` VARCHAR(25) NULL DEFAULT NULL,
  `cidade` VARCHAR(20) NULL DEFAULT NULL,
  `estado` VARCHAR(2) NULL DEFAULT NULL,
  `cep` VARCHAR(8) NULL DEFAULT NULL,
  `sexo` VARCHAR(1) NULL DEFAULT NULL,
  `telefone` INT(11) NULL DEFAULT NULL,
  `celular` INT(11) NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `convenio_codigo` INT(10) UNSIGNED NOT NULL,
  `ativo` VARCHAR(1) NULL DEFAULT NULL,
  `deletado` VARCHAR(1) NULL DEFAULT NULL,
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
  `fax` INT(11) NULL DEFAULT NULL,
  `responsavel` VARCHAR(20) NULL DEFAULT NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
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
  `rg` VARCHAR(10) NULL DEFAULT NULL,
  `endereco` VARCHAR(30) NULL DEFAULT NULL,
  `bairro` VARCHAR(20) NULL DEFAULT NULL,
  `cidade` VARCHAR(25) NULL DEFAULT NULL,
  `estado` VARCHAR(2) NULL DEFAULT NULL,
  `cep` VARCHAR(8) NULL DEFAULT NULL,
  `telefone` INT(11) NULL DEFAULT NULL,
  `celular` INT(11) NULL DEFAULT NULL,
  `sexo` VARCHAR(1) NULL DEFAULT NULL,
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
  `dt_hr_fim` DATETIME NULL DEFAULT NULL,
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


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
