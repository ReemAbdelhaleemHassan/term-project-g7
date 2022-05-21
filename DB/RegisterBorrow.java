package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class RegisterBorrow {
    private static int quantity;
    static DatabaseConnection databaseConnection = new DatabaseConnection();

    public static void register(int user_id, int book_id, LocalDate borrowDate, LocalDate returnDate) throws SQLException {
        databaseConnection.connect();


        String sql= null;

        String decrement = null;
//        String s = null;


//        s = "SET FOREIGN_KEY_CHECKS=0;";
        decrement= "UPDATE available_books SET quantity = quantity - 1 WHERE (isbn='"+book_id+"')";
        sql = "INSERT INTO `borrow_book` (`user_id`,`isbn`,`pick_up_date`,`return_date`) VALUES ('"+user_id+"','"+book_id+"','"+borrowDate+"','"+returnDate+"');";


        String getBookQuery = "SELECT * FROM available_books WHERE '" + book_id + "'= isbn ";

        ResultSet rs = databaseConnection.statement.executeQuery(getBookQuery);
        while (rs.next()) {
            quantity = rs.getInt("quantity");
        }


        if(quantity != 0){
//            databaseConnection.statement.executeUpdate(s);

            databaseConnection.statement.executeUpdate(sql);
            databaseConnection.statement.executeUpdate(decrement);
        }
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

    public boolean isQuantity(int isbn) throws SQLException {
        databaseConnection.connect();

        boolean flag = false;
        String returnBookIDQuery = "SELECT isbn, quantity FROM `available_books`";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(returnBookIDQuery);

        while(databaseConnection.resultSet.next()) {
            if(isbn==databaseConnection.resultSet.getInt("isbn")){
                quantity = databaseConnection.resultSet.getInt("quantity");
                if(quantity>0) {
                    flag = true;
                }
                break;
            }
        }
        return flag;
    }

    public boolean isUserInDB(int user_id) throws SQLException {
        databaseConnection.connect();
        boolean flag=false;
        String returnUsernamesQuery = "SELECT user_id From user";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(returnUsernamesQuery);

        while(databaseConnection.resultSet.next()) {
            if(user_id == databaseConnection.resultSet.getInt("user_id")){

                flag = true;
                break;
            }
        }
        return flag;
    }
}
