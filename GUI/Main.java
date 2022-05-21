package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception{

        //........................................................//
        Label introLable =new Label("Library Management System");
        Button startButton = new Button("Let's get Started");
        Button exitButton=new Button("Exit");


        VBox introVbox =new VBox();
        introVbox.setSpacing(30);
        introVbox.setPadding(new Insets(10,10,10,10));
        introVbox.setAlignment(Pos.CENTER);
        introVbox.getChildren().addAll(introLable,startButton,exitButton);

        Scene introScene =new Scene(introVbox,800,600);
        introScene.getStylesheets().add("file:library.css");

        //........................................................//
        startButton.setOnAction(e->{
            Login login=new Login();
            login.launchLogin(window,introScene);
        });
        //........................................................//
        exitButton.setOnAction(e->{
            window.close();
        });

        //........................................................//
        window.setScene(introScene);
        window.setTitle("Library Management System");
        window.setMaximized(true);
        window.show();
        //........................................................//



    }


    public static void main(String[] args) {
        launch(args);
    }
}

