package tests;


import DB.DatabaseConnection;
import DB.DeleteLibrarian;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DeleteLibrarianTest {
    private static String username ="farah";
    DatabaseConnection databaseConnection = new DatabaseConnection();
    DeleteLibrarian deleteLibrarian= new DeleteLibrarian();

    @Test
    void deleteLibrarianTest() throws SQLException {
       databaseConnection.connect();
       deleteLibrarian.deleteLibrarian(username);

       String testQuery="SELECT * FROM librarian WHERE user_name='farah'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            username = rs.getString("user_name");
           assertNull(username);
        }
    }
}