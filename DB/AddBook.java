package DB;

import java.sql.*;

public class AddBook {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    public void addbook(String name,String genre,String author,String publisher,int quantity) throws SQLException {
        databaseConnection.connect();
        String sql = "INSERT INTO `available_books` (`book_name`, `genre`, `author`, `publisher`, `quantity`) VALUES ('"+name+"', '"+genre+"', '"+author+"', '"+publisher+"', '"+quantity+"');";
        databaseConnection.statement.executeUpdate(sql);
    }

    public boolean isBookInDB(int isbn) throws SQLException {
        databaseConnection.connect();
        boolean flag=false;
        String returnUsernamesQuery = "SELECT isbn From available_books";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(returnUsernamesQuery);

        while(databaseConnection.resultSet.next()) {
            if(isbn == databaseConnection.resultSet.getInt("isbn")){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
