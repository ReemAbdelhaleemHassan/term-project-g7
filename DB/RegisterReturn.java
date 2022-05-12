package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterReturn {
    int id;
    String bookName;
    String genre;
    String author;
    String publisher;
    int quantity;

    public void registerReturn(int user_id, int book_id) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String updateAvailableBooks=null;
        String increment = null;
        String getBookQuery = "SELECT * FROM available_books WHERE '"+book_id+"'= isbn ";

        ResultSet rs = databaseConnection.statement.executeQuery(getBookQuery);
        while(rs.next()){
            id = rs.getInt("isbn");
           bookName = rs.getString("book_name");
             genre = rs.getString("genre");
             author = rs.getString("author");
             publisher = rs.getString("publisher");
             quantity = rs.getInt("quantity");
        }



            //register return book
        if(quantity == 0){
            updateAvailableBooks = "INSERT INTO `available_books` (`isbn`,`book_name`,`genre`,`author`,`publisher`,`quantity`) VALUES ('"+id+"','"+bookName+"','"+genre+"','"+author+"','"+publisher+"',1);";
            databaseConnection.statement.executeUpdate(updateAvailableBooks);
        }else{
            //incerement quantity of this book
            increment = "UPDATE available_books SET quantity = quantity + 1 WHERE (isbn='"+book_id+"')";
            databaseConnection.statement.executeUpdate(increment);
        }



    }
}
