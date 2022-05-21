package DB;

import java.sql.SQLException;

public class AddUser {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    public void addUser(String first_name, int age, int phone_number, String address, String city, String user_name, String password, int cases) throws SQLException {
        databaseConnection.connect();
        String sql = null;
        if (cases == 1){
            sql = "INSERT INTO `user` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`) VALUES ('"+first_name+"','"+user_name+"','"+password+"','"+age+"','"+phone_number+"','"+address+"','"+city+"');";
        } else if (cases == 2) {
            sql = "INSERT INTO `librarian` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`) VALUES ('"+first_name+"','"+user_name+"','"+password+"','"+age+"','"+phone_number+"','"+address+"','"+city+"');";
        }
        databaseConnection.statement.executeUpdate(sql);
    }
    public boolean isUserInDB(String username) throws SQLException {
        databaseConnection.connect();
        boolean flag=false;
        String returnUsernamesQuery = "SELECT user_name From user";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(returnUsernamesQuery);

        while(databaseConnection.resultSet.next()) {
            if(username.equals(databaseConnection.resultSet.getString("user_name"))){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isLibrarianInDB(String username) throws SQLException {
        databaseConnection.connect();
        boolean flag=false;
        String returnUsernamesQuery = "SELECT user_name From librarian";
        databaseConnection.resultSet = databaseConnection.statement.executeQuery(returnUsernamesQuery);

        while(databaseConnection.resultSet.next()) {
            if(username.equals(databaseConnection.resultSet.getString("user_name"))){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
