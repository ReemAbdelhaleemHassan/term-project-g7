package DB;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddBookTest {
    private static String name;

    @Test
    void addbook() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        AddBook addBook = new AddBook();
        addBook.addbook("ketab","genre","author","publisher",6);
        String testQuery= "SELECT * FROM available_books WHERE book_name='ketab'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            name = rs.getString("book_name");
        }
       assertEquals("ketab",name);
       databaseConnection.close();
    }
}