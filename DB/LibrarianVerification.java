package DB;

import java.sql.SQLException;

public class LibrarianVerification {
    public void verifyLibrarian(String userName ,String password ) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "select user_name, password from librarian";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);
        while(databaseConnection.resultSet.next()) {
            System.out.println(databaseConnection.resultSet.getString("user_name"));
            if(userName.equals(databaseConnection.resultSet.getString("user_name")) && password.equals(databaseConnection.resultSet.getString("password"))){
                System.out.println("Verified");
            }
        }
    }
}
