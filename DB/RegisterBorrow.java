package DB;

import java.sql.SQLException;
import java.time.LocalDate;


public class RegisterBorrow {
    public void register(int user_id, int book_id, LocalDate borrowDate,LocalDate returnDate) throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql= null;
        String decrement = null;


            // register Borrow Book
            sql = "INSERT INTO `borrow_book` (`user_id`,`isbn`,`pick_up_date`,`return_date`) VALUES ('"+user_id+"','"+book_id+"','"+borrowDate+"','"+returnDate+"');";
            //decrement quantity of this book
            decrement= "UPDATE available_books SET quantity = quantity - 1 WHERE (isbn='"+book_id+"')";

        databaseConnection.statement.executeUpdate(sql);
        databaseConnection.statement.executeUpdate(decrement);
    }
}
