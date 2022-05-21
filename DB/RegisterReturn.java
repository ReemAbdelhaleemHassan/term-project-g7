package DB;

import java.sql.*;
public class RegisterReturn {
    String deleteBorrowedBooks = null;
    String increment = null;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    public  void registerReturn(int user_id, int book_id) throws SQLException {

        databaseConnection.connect();

        deleteBorrowedBooks = "DELETE FROM borrow_book WHERE `borrow_book`.`user_id` = '"+  user_id +"' AND `borrow_book`.`isbn` = '"+ book_id +"';";

        databaseConnection.statement.executeUpdate(deleteBorrowedBooks);

        //register return book
        //incerement quantity of this book
        increment = "UPDATE available_books SET quantity = quantity + 1 WHERE (isbn='"+book_id+"')";
        databaseConnection.statement.executeUpdate(increment);
    }

    public boolean isInfoCorrect(int userId, int isbn) throws SQLException {

        databaseConnection.connect();

        boolean flag = false;
        String returnUserIDQuery = "SELECT user_id, isbn FROM `borrow_book`";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(returnUserIDQuery);

        while(databaseConnection.resultSet.next()) {
            if(userId==databaseConnection.resultSet.getInt("user_id") && isbn==databaseConnection.resultSet.getInt("isbn")){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
