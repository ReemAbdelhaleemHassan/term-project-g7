CREATE SCHEMA IF NOT EXISTS `LIBRARY_SCHEMA` DEFAULT CHARACTER SET utf8 ;
USE `LIBRARY_SCHEMA` ;

insert into `available_books` (book_name,genre,author,publisher,quantity)
values
("harry potter","fiction","jk rowling","Bloomsbury",5),
("introduction to algorithms","education","ronald rivest","CLRS",7);

