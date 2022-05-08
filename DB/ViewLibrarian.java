package DB;

import java.sql.*;

public class ViewLibrarian {
    public void viewLibrarian() throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "select * from librarian";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);
        while(databaseConnection.resultSet.next()) {
            System.out.println(databaseConnection.resultSet.getString("user_name"));
        }
    }
}