package DB;

import java.sql.SQLException;

public class UserVerification {
    public void verifyUser(String userName ,String password ) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "select user_name, password from user";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);
        while(databaseConnection.resultSet.next()) {
            System.out.println(databaseConnection.resultSet.getString("user_name"));
            if(userName.equals(databaseConnection.resultSet.getString("user_name")) && password.equals(databaseConnection.resultSet.getString("password"))){
                System.out.println("Verified");
            }
        }
    }
}
