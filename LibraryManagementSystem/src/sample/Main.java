package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception{
        Duration duration1=Duration.millis(500);
        Duration duration2=Duration.millis(750);
        Duration duration3=Duration.millis(1000);

        Animation animation=new Animation();

        //........................................................//
        Label introLable =new Label("Library Management System");

        Button userLoginButton = new Button("user Login");
        Button librarianLoginButton = new Button("Librarian Login");
        Button adminLoginButton = new Button("Admin Login");

        VBox introVbox =new VBox();
        introVbox.setSpacing(30);
        introVbox.setPadding(new Insets(10,10,10,10));
        introVbox.setAlignment(Pos.CENTER);
        introVbox.getChildren().addAll(introLable,userLoginButton,librarianLoginButton,adminLoginButton);

        Scene introScene =new Scene(introVbox,800,600);
        introScene.getStylesheets().add("file:library.css");

        //........................................................//
        userLoginButton.setOnAction(e->{
            LibraryUserLogin libraryUserLogin=new LibraryUserLogin();
            libraryUserLogin.launchUserLogin(window,introScene);
        });
        librarianLoginButton.setOnAction(e->{
            LibrarianLogin librarianLogin=new LibrarianLogin();
            librarianLogin.launchLibrarianLogin(window,introScene);

        });
        adminLoginButton.setOnAction(e->{
            AdminLogin adminLogin=new AdminLogin();
            adminLogin.launchAdminLogin(window,introScene);
        });
        //........................................................//
        window.setScene(introScene);
        window.setTitle("Library Management System");
        window.show();
        //........................................................//
        animation.parallelTransition(userLoginButton,duration1);
        animation.parallelTransition(librarianLoginButton,duration2);
        animation.parallelTransition(adminLoginButton,duration3);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
