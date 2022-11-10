SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema warehouse
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema warehouse
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `warehouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `warehouse` ;

CREATE TABLE IF NOT EXISTS `warehouse`.`keys` (
  `id_keys` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_keys`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 65
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `warehouse`.`admins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`admins` (
  `idadmins` INT NOT NULL AUTO_INCREMENT,
  `id_keys` INT NOT NULL,
  PRIMARY KEY (`idadmins`),
  UNIQUE INDEX `id_keys_UNIQUE` (`id_keys` ASC) VISIBLE,
  CONSTRAINT `fk_admins_keys`
    FOREIGN KEY (`id_keys`)
    REFERENCES `warehouse`.`keys` (`id_keys`)
ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `warehouse`.`storages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`storages` (
  `idstorage` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idstorage`),
  UNIQUE INDEX `storage_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `warehouse`.`workers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`workers` (
  `idworker` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `id_keys` INT NOT NULL,
  `idstorage` INT NOT NULL,
  PRIMARY KEY (`idworker`),
  UNIQUE INDEX `id_keys_UNIQUE` (`id_keys` ASC) VISIBLE,
  INDEX `fk_workers_keys_idx` (`id_keys` ASC) VISIBLE,
  INDEX `fk_workers_storages_idx` (`idstorage` ASC) VISIBLE,
  CONSTRAINT `fk_workers_keys`
    FOREIGN KEY (`id_keys`)
    REFERENCES `warehouse`.`keys` (`id_keys`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_workers_storages`
    FOREIGN KEY (`idstorage`)
    REFERENCES `warehouse`.`storages` (`idstorage`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
