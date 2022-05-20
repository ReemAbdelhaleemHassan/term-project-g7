package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        boolean flag=false;
        ArrayList<String> usernames = new ArrayList<>();
        String returnUsernamesQuery = "SELECT user_name From user";
        ResultSet rs = databaseConnection.statement.executeQuery(returnUsernamesQuery);

        for (int i=1; rs.next(); i++)
        {
            usernames.add( rs.getString(i) );
        }

        for(int j=0;j<=usernames.size();j++){
            if(username== usernames.get(j)){
                flag = true;
                break;
            }
        }
        System.out.println(flag);
        return flag;
    }
}
