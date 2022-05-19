package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class RegisterBorrow {
    private static int quantity;

    public static void register(int user_id, int book_id, LocalDate borrowDate, LocalDate returnDate) throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        String sql= null;
        String decrement = null;
        String s = null;

        //register Borrow Book
        //decrement quantity of this book
        s = "SET FOREIGN_KEY_CHECKS=0;";
        decrement= "UPDATE available_books SET quantity = quantity - 1 WHERE (isbn='"+book_id+"')";
        sql = "INSERT INTO `borrow_book` (`user_id`,`isbn`,`pick_up_date`,`return_date`) VALUES ('"+user_id+"','"+book_id+"','"+borrowDate+"','"+returnDate+"');";
        databaseConnection.statement.executeUpdate(s);
        databaseConnection.statement.executeUpdate(sql);
        databaseConnection.statement.executeUpdate(decrement);

    }
}
