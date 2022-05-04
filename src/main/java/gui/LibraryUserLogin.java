package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryUserLogin {
    private String userEmail;
    private String userPassword;

    public void launchUserLogin(Stage window , Scene previousScene){


        Label introLable=new Label("user Login");
        Label userEmailLable=new Label("email");
        Label userPasswordLable=new Label("password");

        TextField userEmailTextfield =new TextField();
        PasswordField userPasswordTextfield =new PasswordField();

        Button userLoginButton =new Button("login");
        Button createAccountButton= new Button("dont have an account?");
        Button returnButton=new Button("return");

        VBox userVbox =new VBox();
        userVbox.setSpacing(10);
        userVbox.setPadding(new Insets(10,10,10,10));
        userVbox.setAlignment(Pos.CENTER);
        userVbox.getChildren().addAll(introLable,userEmailLable,userEmailTextfield,userPasswordLable,userPasswordTextfield,userLoginButton,createAccountButton,returnButton);

        Scene userLoginScene =new Scene(userVbox,800,600);
        userLoginScene.getStylesheets().add("file:library.css");
        window.setScene(userLoginScene);
        window.show();

        //........................................................//
        userLoginButton.setOnAction(e->{
            userEmail =userEmailTextfield.getText();
            userPassword =userPasswordTextfield.getText();
            verifyLogin(userEmail, userPassword,window,userLoginScene);
        });
        createAccountButton.setOnAction(e->{
            LibraryUser libraryUser =new LibraryUser();
            libraryUser.newUser(window,userLoginScene);
        });
        returnButton.setOnAction(e->{
            window.setScene(previousScene);
        });

    }
    public void verifyLogin(String email ,String password,Stage window,Scene userLoginScene){
        //Todo verification
        UserDashboard userDashboard=new UserDashboard();
        userDashboard.launchUserDashboard(window,userLoginScene);
    }
}
