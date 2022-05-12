package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


public class RegisterBorrow {
    int quantity;
    public void register(int user_id, int book_id, LocalDate borrowDate,LocalDate returnDate) throws SQLException {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql= null;
        String decrement = null;
        String delete = null;
        String getBookQuery = "SELECT * FROM available_books WHERE '"+book_id+"'= isbn ";

        ResultSet rs = databaseConnection.statement.executeQuery(getBookQuery);
        while(rs.next()){
            quantity = rs.getInt("quantity");
        }


        if(quantity>=0){
            // register Borrow Book
            //decrement quantity of this book
            decrement= "UPDATE available_books SET quantity = quantity - 1 WHERE (isbn='"+book_id+"')";
            sql = "INSERT INTO `borrow_book` (`user_id`,`isbn`,`pick_up_date`,`return_date`) VALUES ('"+user_id+"','"+book_id+"','"+borrowDate+"','"+returnDate+"');";
            databaseConnection.statement.executeUpdate(sql);
            databaseConnection.statement.executeUpdate(decrement);
        }else{
            delete = "DELETE FROM available_books WHERE isbn='"+book_id+"'";
            databaseConnection.statement.executeUpdate(delete);

        }



    }
}
