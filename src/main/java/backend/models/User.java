package main.java.backend.models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class User {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;


    public void setUserInformation(ResultSet resultSet) throws SQLException {
       
                this.setId(resultSet.getInt("UserId"));
                this.setFirstName(resultSet.getString("FirstName"));
                this.setLastName(resultSet.getString("LastName"));
                this.setUsername(resultSet.getString("username"));
                this.setPassword(resultSet.getString("password"));
                this.setEmail(resultSet.getString("email"));
                this.setPhoneNumber(resultSet.getString("phone"));
    }

    public void editPersonalInfo(UserBasicInfo user) throws SQLException {
       //update user info
    }

    public boolean signIn(String username,String password)throws SQLException{
        //check db for user and return true or false
    }

    public int getUserId(String username) throws SQLException {
        //get userid from db based on their username
    }

    public int signUp (UserBasicInfo newUser)throws SQLException{
        //signup
    }


}
