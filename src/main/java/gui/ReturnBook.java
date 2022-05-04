package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class ReturnBook {
    int userID;
    int bookID;

    public void returnBook(Stage window, Scene previous) {
        Label introLable = new Label("register a return");
        Label userIdLable = new Label("user Id");
        Label bookIdLable = new Label("book Id");

        TextField userIdTextField = new TextField();
        TextField bookIdTextField = new TextField();

        Button registerReturnButton = new Button("register");
        Button returnButton = new Button("return");

        VBox registerReturnVbox = new VBox();
        registerReturnVbox.setSpacing(30);
        registerReturnVbox.setPadding(new Insets(10, 10, 10, 10));
        registerReturnVbox.setAlignment(Pos.CENTER);
        registerReturnVbox.getChildren().addAll(introLable, userIdLable, userIdTextField, bookIdLable, bookIdTextField, registerReturnButton, returnButton);


        Scene registerReturnScene = new Scene(registerReturnVbox, 800, 600);
        registerReturnScene.getStylesheets().add("file:library.css");
        window.setScene(registerReturnScene);
        window.show();

        registerReturnButton.setOnAction(e -> {
            //todo
            userID = Integer.parseInt(userIdTextField.getText());
            bookID = Integer.parseInt(bookIdTextField.getText());


        });

        returnButton.setOnAction(e -> {
            window.setScene(previous);
        });

    }
}
