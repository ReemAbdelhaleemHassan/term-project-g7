package src.main.java.gui;

import DB.AddUser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;


import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UserInterface extends User {
    public void newUser(Stage window, Scene previous, int cases){
        Label introLable=new Label("create account");
        Label nameLable=new Label("Name");
        Label ageLable=new Label("Age");
        Label phoneNumberLable=new Label("PhoneNumber");
        Label addressLable=new Label("Address");
        Label cityLable=new Label("city");
        Label usernameLable=new Label("username");
        Label passwordLable=new Label("password");

        TextField nameTextField =new TextField();
        TextField ageTextField =new TextField();
        TextField phoneNumberTextField =new TextField();
        TextField addressTextField =new TextField();
        TextField cityTextField =new TextField();
        TextField usernameTextField =new TextField();
        PasswordField passwordTextfield=new PasswordField();

        Button createButton =new Button("Create");
        Button returnButton=new Button("return");

        HBox hBox1=new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(30);
        hBox1.getChildren().addAll(nameLable,nameTextField);

        HBox hBox2=new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setSpacing(30);
        hBox2.getChildren().addAll(ageLable,ageTextField);

        HBox hBox3=new HBox();
        hBox3.setAlignment(Pos.CENTER);
        hBox3.setSpacing(30);
        hBox3.getChildren().addAll(phoneNumberLable,phoneNumberTextField);

        HBox hBox4=new HBox();
        hBox4.setAlignment(Pos.CENTER);
        hBox4.setSpacing(30);
        hBox4.getChildren().addAll(addressLable,addressTextField);

        HBox hBox5=new HBox();
        hBox5.setAlignment(Pos.CENTER);
        hBox5.setSpacing(30);
        hBox5.getChildren().addAll(cityLable,cityTextField);

        HBox hBox6=new HBox();
        hBox6.setAlignment(Pos.CENTER);
        hBox6.setSpacing(30);
        hBox6.getChildren().addAll(usernameLable,usernameTextField);

        HBox hBox7=new HBox();
        hBox7.setAlignment(Pos.CENTER);
        hBox7.setSpacing(30);
        hBox7.getChildren().addAll(passwordLable,passwordTextfield);


        VBox createUserAccountVbox=new VBox();
        createUserAccountVbox.setSpacing(30);
        createUserAccountVbox.setPadding(new Insets(10,10,10,10));
        createUserAccountVbox.setAlignment(Pos.CENTER);
        createUserAccountVbox.getChildren().addAll(introLable,hBox1,hBox2,hBox3,hBox4,hBox5,hBox6,hBox7,createButton,returnButton);

        Scene createUserAccountScene=new Scene(createUserAccountVbox,800,600);
        createUserAccountScene.getStylesheets().add("file:library.css");
        window.setScene(createUserAccountScene);
        window.show();

        createButton.setOnAction(e->{
            name=nameTextField.getText();
            String age_string=ageTextField.getText();
            String phoneNumber_string =phoneNumberTextField.getText();
            address=addressTextField.getText();
            city=cityTextField.getText();
            username =usernameTextField.getText();
            password=passwordTextfield.getText();


            int ishandled=HandleEmptyText(name,age_string,phoneNumber_string,address,city,username,password);
            if (ishandled==1){
                //todo:save in database
                age = Integer.parseInt(age_string);
                phoneNumber = Integer.parseInt(phoneNumber_string);
                AddUser addUser = new AddUser();
                try {
                    addUser.addUser(name, age, phoneNumber, address, city, username, password, cases);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });
    }
    int HandleEmptyText(String name,String age,String phonenumber,String address,String city,String username,String password){
        int flag=1;
        if (name.equals("")||age.equals("")||phonenumber.equals("")||address.equals("")||city.equals("")||username.equals("")||password.equals("")){
            errorMessage();
            flag=0;
        }
        return flag;
    }
    private void errorMessage(){
        Stage errorWindow =new Stage();
        Label errorLable=new Label("please enter all information required");
        Button okButton =new Button("OK");

        VBox errorVBox=new VBox();
        errorVBox.setSpacing(30);
        errorVBox.setAlignment(Pos.CENTER);
        errorVBox.getChildren().addAll(errorLable,okButton);

        okButton.setOnAction(e->{
            errorWindow.close();
        });

        Scene errorScene =new Scene(errorVBox,400,150);
        errorScene.getStylesheets().addAll("file:library.css");

        errorWindow.setScene(errorScene);
        errorWindow.setTitle("error");
        errorWindow.showAndWait();
    }

}
