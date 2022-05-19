package DB;

import java.sql.*;

public class DatabaseConnection {
    Statement statement;
    ResultSet resultSet;
    public void connect(){
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_schema","root","password");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
