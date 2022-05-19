package DB;

import java.sql.SQLException;

public class AddUser {
    public void addUser(String first_name, int age, int phone_number, String address, String city, String user_name, String password, int cases) throws SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        String sql = null;
        if (cases == 1){
            sql = "INSERT INTO `user` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`) VALUES ('"+first_name+"','"+user_name+"','"+password+"','"+age+"','"+phone_number+"','"+address+"','"+city+"');";
        } else if (cases == 2) {
            sql = "INSERT INTO `librarian` (`first_name`, `user_name`, `password`, `age`, `phone_number`, `address`, `city`) VALUES ('"+first_name+"','"+user_name+"','"+password+"','"+age+"','"+phone_number+"','"+address+"','"+city+"');";
        }
        databaseConnection.statement.executeUpdate(sql);
    }
}
