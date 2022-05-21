package DB;

import java.sql.SQLException;

public class UserVerification {
    public int verifyUser(String userName ,String password, int cases) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = null;

        if(cases==1){
            sql = "select user_name, password from user";
        }else if(cases==2){
            sql = "select user_name, password from librarian";
        }

        databaseConnection.resultSet = databaseConnection.statement.executeQuery(sql);
        while(databaseConnection.resultSet.next()) {
            if(userName.equals(databaseConnection.resultSet.getString("user_name")) && password.equals(databaseConnection.resultSet.getString("password"))){
                return 1;
            }
        }
        return 0;
    }
}
