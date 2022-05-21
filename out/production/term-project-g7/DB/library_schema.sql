
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


CREATE SCHEMA IF NOT EXISTS `LIBRARY_SCHEMA` DEFAULT CHARACTER SET utf8 ;
USE `LIBRARY_SCHEMA` ;

CREATE TABLE if not exists `library_schema`.`user` (
    user_id int AUTO_INCREMENT PRIMARY KEY,
    first_name varchar (50)not null,
    user_name VARCHAR(30) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    age int NOT NULL,
    phone_number INT(11) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    CONSTRAINT unique_user_name UNIQUE (user_name)
);

CREATE TABLE if not exists `library_schema`.`librarian` (
    librarian_id int AUTO_INCREMENT PRIMARY KEY,
    first_name varchar (50)not null,
    user_name VARCHAR(30) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    age int NOT NULL,
    phone_number INT(11) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    CONSTRAINT unique_librarian_user_name UNIQUE (user_name)
);


CREATE TABLE  if not exists `library_schema`.`available_books` (
    isbn int AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(30) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    quantity int not null
);


CREATE TABLE  if not exists `library_schema`.`borrow_book` (
    user_id int not null,
    isbn int not null,
    pick_up_date DATE not null,
    return_date DATE not null,
    PRIMARY KEY (user_id , isbn),
    FOREIGN KEY (user_id)
        REFERENCES user (user_id),
    FOREIGN KEY (isbn)
        REFERENCES available_books (isbn)
);