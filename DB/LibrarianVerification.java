package DB;

import java.sql.*;

public class LibrarianVerification {
    public int verifyLibrarian(String userName ,String password ) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "select user_name, password from librarian";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);
        while(databaseConnection.resultSet.next()) {
            if(userName.equals(databaseConnection.resultSet.getString("user_name")) && password.equals(databaseConnection.resultSet.getString("password"))){
                return 1 ;
            }
        }
        return 0;
    }
}
