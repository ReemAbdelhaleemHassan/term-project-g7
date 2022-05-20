package tests;

import DB.AddBook;
import DB.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddBookTest {
    private static String name;
    private static int quantity;

    @Test
    void testQueryCorrect() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        AddBook addBook = new AddBook();
        addBook.addbook("ketab","genre","author","publisher",6);
        String testQuery= "SELECT * FROM available_books WHERE book_name='ketab'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            name = rs.getString("book_name");
            quantity=rs.getInt("quantity");
        }
       assertEquals("ketab",name);
        assertTrue(quantity>0);


       databaseConnection.close();
    }

    @Test
    void testQuantityAboveZero() throws SQLException{
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        AddBook addBook = new AddBook();
        addBook.addbook("ketab","genre","author","publisher",6);
        String testQuery= "SELECT * FROM available_books WHERE book_name='ketab'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            quantity=rs.getInt("quantity");
        }
        assertTrue(quantity>0);
        databaseConnection.close();
    }
}