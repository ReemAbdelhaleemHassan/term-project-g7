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

public class AdminLogin {
    private String adminEmail;
    private String adminPassword;

    public void launchAdminLogin(Stage window ,Scene previousScene){


        Label introLable=new Label("Admin Login");
        Label adminEmailLable=new Label("email");
        Label adminPasswordLable=new Label("password");

        TextField adminEmailTextfield =new TextField();
        PasswordField adminPasswordTextfield =new PasswordField();

        Button adminLoginButton =new Button("login");
        Button returnButton=new Button("return");

        VBox adminVbox =new VBox();
        adminVbox.setSpacing(30);
        adminVbox.setPadding(new Insets(10,10,10,10));
        adminVbox.setAlignment(Pos.CENTER);
        adminVbox.getChildren().addAll(introLable,adminEmailLable,adminEmailTextfield,adminPasswordLable,adminPasswordTextfield,adminLoginButton,returnButton);

        Scene adminLoginScene =new Scene(adminVbox,800,600);
        adminLoginScene.getStylesheets().add("file:library.css");
        window.setScene(adminLoginScene);
        window.show();

        //........................................................//
        adminLoginButton.setOnAction(e->{
            adminEmail=adminEmailTextfield.getText();
            adminPassword=adminPasswordTextfield.getText();
            verifyLogin(adminEmail,adminPassword,window,adminLoginScene);
        });
        returnButton.setOnAction(e->{
            window.setScene(previousScene);
        });

    }
    public void verifyLogin(String email ,String password,Stage window,Scene adminloginScene){
        //Todo verification
        AdminDashboard adminDashboard=new AdminDashboard();
        adminDashboard.launchAdminDashboard(window,adminloginScene);
    }
}