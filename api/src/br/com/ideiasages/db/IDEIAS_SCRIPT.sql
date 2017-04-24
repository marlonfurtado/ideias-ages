-- MySQL Workbench Synchronization
-- Generated: 2017-04-24 16:01
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: 16204179

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `ideias`.`user` (
  `cpf` VARCHAR(11) NOT NULL COMMENT '',
  `email` VARCHAR(100) NOT NULL COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `phone` VARCHAR(11) NULL DEFAULT NULL COMMENT '',
  `password` VARCHAR(255) NOT NULL COMMENT '',
  `active` TINYINT(1) NOT NULL COMMENT '',
  `role_name` VARCHAR(80) NOT NULL COMMENT '',
  INDEX `FK_ROLE_idx` (`role_name` ASC)  COMMENT '',
  PRIMARY KEY (`cpf`)  COMMENT '',
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)  COMMENT '',
  CONSTRAINT `FK_ROLE`
    FOREIGN KEY (`role_name`)
    REFERENCES `ideias`.`role` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`role` (
  `name` VARCHAR(80) NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`idea` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(100) NOT NULL COMMENT '',
  `description` MEDIUMTEXT NOT NULL COMMENT '',
  `status_name` VARCHAR(100) NOT NULL COMMENT '',
  `tags` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `user_cpf` VARCHAR(11) NOT NULL COMMENT '',
  `goal` VARCHAR(100) NOT NULL COMMENT '',
  INDEX `FK_USER_idx` (`user_cpf` ASC)  COMMENT '',
  INDEX `FK_STATUS_idx` (`status_name` ASC)  COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  CONSTRAINT `FK_USER`
    FOREIGN KEY (`user_cpf`)
    REFERENCES `ideias`.`user` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_STATUS`
    FOREIGN KEY (`status_name`)
    REFERENCES `ideias`.`status` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`status` (
  `name` VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (`name`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`idea_comments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `comment` MEDIUMTEXT NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`idea_has_idea_comments` (
  `idea_id` INT(11) NOT NULL COMMENT '',
  `idea_comments_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`idea_id`, `idea_comments_id`)  COMMENT '',
  INDEX `fk_idea_has_idea_comments_idea_comments1_idx` (`idea_comments_id` ASC)  COMMENT '',
  INDEX `fk_idea_has_idea_comments_idea1_idx` (`idea_id` ASC)  COMMENT '',
  CONSTRAINT `fk_idea_has_idea_comments_idea1`
    FOREIGN KEY (`idea_id`)
    REFERENCES `ideias`.`idea` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idea_has_idea_comments_idea_comments1`
    FOREIGN KEY (`idea_comments_id`)
    REFERENCES `ideias`.`idea_comments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`questions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `question` MEDIUMTEXT NOT NULL COMMENT '',
  `user_cpf` VARCHAR(11) NOT NULL COMMENT '',
  `answer` MEDIUMTEXT NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `FK_user_idx2` (`user_cpf` ASC)  COMMENT '',
  CONSTRAINT `FK_user2`
    FOREIGN KEY (`user_cpf`)
    REFERENCES `ideias`.`user` (`cpf`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS `ideias`.`idea_has_questions` (
  `idea_id` INT(11) NOT NULL COMMENT '',
  `questions_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`idea_id`, `questions_id`)  COMMENT '',
  INDEX `fk_idea_has_questions_questions1_idx` (`questions_id` ASC)  COMMENT '',
  INDEX `fk_idea_has_questions_idea1_idx` (`idea_id` ASC)  COMMENT '',
  CONSTRAINT `fk_idea_has_questions_idea1`
    FOREIGN KEY (`idea_id`)
    REFERENCES `ideias`.`idea` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_idea_has_questions_questions1`
    FOREIGN KEY (`questions_id`)
    REFERENCES `ideias`.`questions` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
