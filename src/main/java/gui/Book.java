package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class Book {
    int bookID;
    String name;
    String genre;
    String author;
    String publisher;
    int quantity;
    boolean status;

    public void newBook(Stage window ,Scene previous){
        Label introLable=new Label("new Book");
        Label nameLabel=new Label("Name");
        Label genreLabel=new Label("Genre");
        Label authorLabel=new Label("Author");
        Label publisherLabel=new Label("Publisher");
        Label quantityLabel=new Label("Quantity");

        TextField nameTextField =new TextField("");
        TextField genreTextField =new TextField("");
        TextField authorTextField =new TextField("");
        TextField publisherTextField =new TextField("");
        TextField quantityTextField =new TextField("");

        Button addBookButton=new Button("Add");
        Button returnButton=new Button("return");

        VBox newBookVbox=new VBox();
        newBookVbox.setSpacing(30);
        newBookVbox.setPadding(new Insets(10,10,10,10));
        newBookVbox.setAlignment(Pos.CENTER);
        newBookVbox.getChildren().addAll(introLable,nameLabel,nameTextField,genreLabel,genreTextField,authorLabel,authorTextField,publisherLabel,publisherTextField,quantityLabel,quantityTextField,addBookButton,returnButton);

        Scene addBookScene=new Scene(newBookVbox,800,600);
        addBookScene.getStylesheets().add("file:library.css");
        window.setScene(addBookScene);
        window.show();

        addBookButton.setOnAction(e->{
            Random rand =new Random();
            bookID=rand.nextInt(1000)+1; //todo create unique ID
            name =nameTextField.getText();
            genre=genreTextField.getText();
            author=authorTextField.getText();
            publisher=publisherTextField.getText();
            quantity=Integer.parseInt(quantityTextField.getText());
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });

    }
}
