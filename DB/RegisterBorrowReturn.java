package DB;

import java.sql.SQLException;

public class RegisterBorrowReturn {
    public void register(int user_id, int book_id, int cases) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        if (cases == 1){
            // register Borrow Book
            //sql =
            //decrement quantity of this book
        }else if(cases == 2){
            //register return book
            //sql =
            //incerement quantity of this book
        }
        //databaseConnection.statement.executeUpdate(sql);
    }
}
