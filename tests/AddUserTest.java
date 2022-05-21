package tests;

import DB.AddUser;
import DB.DatabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddUserTest {
private static int age;
private static String password;

    DatabaseConnection databaseConnection = new DatabaseConnection();
    AddUser addUser= new AddUser();

    @Test
    void verifyUserAge() throws SQLException {

        databaseConnection.connect();

        addUser.addUser("name",19,234,"alex","alex","username","password",1);

        String testQuery= "SELECT * FROM user WHERE first_name='name'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            age=rs.getInt("age");
        }
        assertTrue(age>=14);

        databaseConnection.close();
    }

   @Test
    void verifyLibrarianAge() throws SQLException {

        databaseConnection.connect();

        addUser.addUser("name",23,234,"alex","alex","username","password",2);

        String testQuery= "SELECT * FROM librarian WHERE first_name='name'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            age=rs.getInt("age");
        }
        assertTrue(age>=21);

        databaseConnection.close();
    }

    @Test
    void verifyUserPassword() throws SQLException{
        databaseConnection.connect();
        addUser.addUser("name",23,234,"alex","alex","username","password",2);
        String testQuery= "SELECT * FROM user WHERE first_name='name'";

        ResultSet rs = databaseConnection.statement.executeQuery(testQuery);
        while(rs.next()){
            password=rs.getString("password");
        }
        assertTrue(password.length()>=8);

        databaseConnection.close();
    }
}