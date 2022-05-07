package DB;

import java.sql.SQLException;

public class AddLibraryUser {
    public void addLibraryUser(String first_name, int age, int phone_number, String address, String city, String user_name, String password) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = "INSERT INTO `user` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`) VALUES (first_name, user_name, password, age, phone_number, address, city);";
        databaseConnection.statement.executeUpdate(sql);
    }
}
