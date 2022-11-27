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
  `size` INT NULL default NULL,
  PRIMARY KEY (`idstorage`),
  UNIQUE INDEX `storage_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `warehouse`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`products` (
  `idproduct` INT NOT NULL AUTO_INCREMENT,
  `typeName` VARCHAR(45) NOT NULL,
  `idstorage` INT NOT NULL,
  PRIMARY KEY (`idproduct`),
  INDEX `fk_products_storages_idx` (`idstorage` ASC) VISIBLE,
  CONSTRAINT `fk_products_storages`
    FOREIGN KEY (`idstorage`)
    REFERENCES `warehouse`.`storages` (`idstorage`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
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

-- -----------------------------------------------------
-- Table `warehouse`.`payments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`payments` (
  `idpayment` INT NOT NULL AUTO_INCREMENT,
  `payment` INT NOT NULL,
  `balance` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idpayment`),
  UNIQUE INDEX `payment_UNIQUE` (`payment` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `warehouse`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`clients` (
  `idclient` INT NOT NULL AUTO_INCREMENT,
  `companyName` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `id_keys` INT NOT NULL,
  `idpayment` INT NULL DEFAULT NULL,
  PRIMARY KEY (`idclient`),
  UNIQUE INDEX `id_keys_UNIQUE` (`id_keys` ASC) VISIBLE,
  UNIQUE INDEX `idpayment_UNIQUE` (`idpayment` ASC) VISIBLE,
  INDEX `fk_clients_keys_idx` (`id_keys` ASC) VISIBLE,
  CONSTRAINT `fk_clients_keys`
    FOREIGN KEY (`id_keys`)
    REFERENCES `warehouse`.`keys` (`id_keys`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_clients_payments`
    FOREIGN KEY (`idpayment`)
    REFERENCES `warehouse`.`payments` (`idpayment`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

-- -----------------------------------------------------
-- Table `warehouse`.`ttns`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `warehouse`.`ttns` (
  `idttn` INT NOT NULL AUTO_INCREMENT,
  `productName` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `productSize` INT NULL DEFAULT NULL,
  `shelfLifeProd` DATE NULL DEFAULT NULL,
  `idproduct` INT NOT NULL,
  `idworker` INT NOT NULL,
  PRIMARY KEY (`idttn`),
  INDEX `fk_groups_products_idx` (`idproduct` ASC) VISIBLE,
  INDEX `fk_groups_workers_idx` (`idworker` ASC) VISIBLE,
  CONSTRAINT `fk_ttns_products`
    FOREIGN KEY (`idproduct`)
    REFERENCES `warehouse`.`products` (`idproduct`),
  CONSTRAINT `fk_ttbs_workers`
    FOREIGN KEY (`idworker`)
    REFERENCES `warehouse`.`workers` (`idworker`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

