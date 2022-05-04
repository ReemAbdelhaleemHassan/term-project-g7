-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema LIBRARY_SCHEMA
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema LIBRARY_SCHEMA
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LIBRARY_SCHEMA` DEFAULT CHARACTER SET utf8 ;
USE `LIBRARY_SCHEMA` ;

-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`Publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`Publisher` (
  `Name` VARCHAR(100) NOT NULL,
  `Address` VARCHAR(100) NOT NULL,
  `Number` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Name`),
  UNIQUE INDEX `Name_UNIQUE` (`Name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`Book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`Book` (
  `ISBN` INT NOT NULL AUTO_INCREMENT,
  `Title` VARCHAR(100) NOT NULL,
  `Publisher` VARCHAR(100) NOT NULL,
  `Publication_year` VARCHAR(4) NOT NULL,
  `Category` ENUM("Science", "Art", "Religion", "History", "Geography") NOT NULL,
  `Price` INT NOT NULL,
  `Threashold` INT NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`ISBN`),
  INDEX `Publisher.BOOK_idx` (`Publisher` ASC) VISIBLE,
  INDEX `Title.BOOK_idx` (`Title` ASC) VISIBLE,
  INDEX `publicationYear.BOOK_idx` (`Publication_year` ASC) VISIBLE,
  INDEX `Price.BOOK_idx` (`Price` ASC) VISIBLE,
  INDEX `category.BOOK_idx` (`Category` ASC) VISIBLE,
  UNIQUE INDEX `ISBN_UNIQUE` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `Publisher.BOOK`
    FOREIGN KEY (`Publisher`)
    REFERENCES `LIBRARY_SCHEMA`.`Publisher` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`Authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`Authors` (
  `ISBN` INT NOT NULL,
  `Author_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ISBN`, `Author_name`),
  INDEX `author.Authors_idx` (`Author_name` ASC) VISIBLE,
  CONSTRAINT `ISBN.AUTHORS`
    FOREIGN KEY (`ISBN`)
    REFERENCES `LIBRARY_SCHEMA`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`User` (
  `UserId` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `FirstName` VARCHAR(100) NOT NULL,
  `LastName` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`UserId`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `UserId_UNIQUE` (`UserId` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`Customer_Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`Customer_Order` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `Date` DATE NOT NULL,
  `Price` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `user.ORDER_idx` (`UserId` ASC) VISIBLE,
  CONSTRAINT `user.ORDER`
    FOREIGN KEY (`UserId`)
    REFERENCES `LIBRARY_SCHEMA`.`User` (`UserId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`Order_Items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`Order_Items` (
  `orderID` INT NOT NULL,
  `ISBN` INT NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`orderID`, `ISBN`),
  INDEX `ISBN.ITEM_idx` (`ISBN` ASC) VISIBLE,
  CONSTRAINT `order.ORDERITEM`
    FOREIGN KEY (`orderID`)
    REFERENCES `LIBRARY_SCHEMA`.`Customer_Order` (`orderId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `ISBN.ITEM`
    FOREIGN KEY (`ISBN`)
    REFERENCES `LIBRARY_SCHEMA`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `LIBRARY_SCHEMA`.`Library_Orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIBRARY_SCHEMA`.`Library_Orders` (
  `orderId` INT NOT NULL AUTO_INCREMENT,
  `ISBN` INT NOT NULL,
  `Publisher` VARCHAR(100) NOT NULL,
  `Quantity` INT NOT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `ISBN.LIBRARY_idx` (`ISBN` ASC) VISIBLE,
  INDEX `publisher.LIBRARY_idx` (`Publisher` ASC) VISIBLE,
  UNIQUE INDEX `orderId_UNIQUE` (`orderId` ASC) VISIBLE,
  CONSTRAINT `ISBN.LIBRARY`
    FOREIGN KEY (`ISBN`)
    REFERENCES `LIBRARY_SCHEMA`.`Book` (`ISBN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `publisher.LIBRARY`
    FOREIGN KEY (`Publisher`)
    REFERENCES `LIBRARY_SCHEMA`.`Publisher` (`Name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

USE `LIBRARY_SCHEMA`;

DELIMITER $$
USE `LIBRARY_SCHEMA`$$
CREATE DEFINER = CURRENT_USER TRIGGER `LIBRARY_SCHEMA`.`Book_BEFORE_UPDATE` BEFORE UPDATE ON `Book` FOR EACH ROW
BEGIN
	if new.Quantity < 0 then signal sqlstate '45000'
    SET MESSAGE_TEXT = 'Quantity in Stock is less than zero';
    END IF;
END$$

USE `LIBRARY_SCHEMA`$$
CREATE DEFINER = CURRENT_USER TRIGGER `LIBRARY_SCHEMA`.`Book_AFTER_UPDATE` AFTER UPDATE ON `Book` FOR EACH ROW
BEGIN
	IF new.Quantity < old.Threashold then 
    INSERT INTO Library_Orders VALUES (default,new.ISBN,new.Publisher,new.Threashold);
    END IF;
END$$

USE `LIBRARY_SCHEMA`$$
CREATE DEFINER = CURRENT_USER TRIGGER `LIBRARY_SCHEMA`.`Library_Orders_BEFORE_DELETE` BEFORE DELETE ON `Library_Orders` FOR EACH ROW
BEGIN
	UPDATE Book SET Quantity = Quantity+old.Quantity WHERE Book.ISBN=old.ISBN;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
