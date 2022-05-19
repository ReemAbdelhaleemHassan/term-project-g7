package DB;


import java.sql.*;

public class RegisterReturn {
    String deleteBorrowedBooks = null;
    String increment = null;
    public  void registerReturn(int user_id, int book_id) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        deleteBorrowedBooks = "DELETE FROM borrow_book WHERE `borrow_book`.`user_id` = '"+  user_id +"' AND `borrow_book`.`isbn` = '"+ book_id +"';";
        String s = "SET FOREIGN_KEY_CHECKS=0;";

        databaseConnection.statement.executeUpdate(s);
        databaseConnection.statement.executeUpdate(deleteBorrowedBooks);

        //register return book
        //incerement quantity of this book
        increment = "UPDATE available_books SET quantity = quantity + 1 WHERE (isbn='"+book_id+"')";
        databaseConnection.statement.executeUpdate(increment);

    }
}
