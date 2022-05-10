package DB;

import java.sql.*;

public class AddBook {
    public void addbook(String name,String genre,String author,String publisher,int quantity) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "INSERT INTO `available_books` (`book_name`, `genre`, `author`, `publisher`, `quantity`) VALUES ('"+name+"', '"+genre+"', '"+author+"', '"+publisher+"', '"+quantity+"');";
        databaseConnection.statement.executeUpdate(sql);
    }
}
