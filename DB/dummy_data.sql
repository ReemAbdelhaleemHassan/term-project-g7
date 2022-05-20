CREATE SCHEMA IF NOT EXISTS `LIBRARY_SCHEMA` DEFAULT CHARACTER SET utf8 ;
USE `LIBRARY_SCHEMA` ;

insert into `available_books` (book_name,genre,author,publisher,quantity)
values
("harry potter","fiction","jk rowling","Bloomsbury",5),
("introduction to algorithms","education","ronald rivest","CLRS",2),
("The Happy Brain","education","ronald rivest","RTR",3),
("The Idiot Brain","education","ronald rivest","YHE",4),
("Oliver Twist","education","Charles Dickens","POL",6);

INSERT INTO `user` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`)
VALUES
('Khaled', 'Khaled', '1234', '22', '2334', 'Alex', 'Alex'),
('Riham', 'Riham', '1234', '23', '01000', 'fdxv', 'Alex'),
('Reem', 'Reem', '1234', '24', '01555921663', 'fdxv', 'Alex'),
('Kamal', 'Kamal', '1234', '25', '01115437895', 'fdxv', 'Cairo'),
('Gammal', 'Gammal', '1234', '26', '2334', 'fdxv', 'Cairo');

INSERT INTO `librarian` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`)
VALUES
('Mohamed', 'mo', '1234', '22', '2334', 'Alex', 'Alex'),
('Abdelhaleem', 'haleem', '1234', '23', '01000', 'fdxv', 'Alex'),
('Yara', 'yara', '1234', '24', '01555921663', 'fdxv', 'Alex'),
('Farah', 'farah', '1234', '25', '01115437895', 'fdxv', 'Cairo'),
('Saeed', 'saeed', '1234', '26', '2334', 'fdxv', 'Cairo');

INSERT INTO `borrow_book` (`user_id`, `isbn`, `pick_up_date`, `return_date`)
VALUES
('1', '4', '2022-05-17', '2022-05-22'),
('2', '5', '2022-05-18', '2022-05-23'),
('3', '2', '2022-05-19', '2022-05-24'),
('4', '1', '2022-05-20', '2022-05-25'),
('5', '3', '2022-05-21', '2022-05-26');