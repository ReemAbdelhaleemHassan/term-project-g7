package src.main.java.gui;

import DB.AddBook;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

import java.util.Random;


public class Book{
    int bookID;
    String name;
    String genre;
    String author;
    String publisher;
    String quantity_string;
    int quantity;
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

        HBox hBox1 =new HBox();
        hBox1.setSpacing(30);
        hBox1.setPadding(new Insets(10,10,10,10));
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(nameLabel,nameTextField);

        HBox hBox2 =new HBox();
        hBox2.setSpacing(30);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(genreLabel,genreTextField);

        HBox hBox3 =new HBox();
        hBox3.setSpacing(30);
        hBox3.setPadding(new Insets(10,10,10,10));
        hBox3.setAlignment(Pos.CENTER);
        hBox3.getChildren().addAll(authorLabel,authorTextField);

        HBox hBox4 =new HBox();
        hBox4.setSpacing(30);
        hBox4.setPadding(new Insets(10,10,10,10));
        hBox4.setAlignment(Pos.CENTER);
        hBox4.getChildren().addAll(publisherLabel,publisherTextField);

        HBox hBox5 =new HBox();
        hBox5.setSpacing(30);
        hBox5.setPadding(new Insets(10,10,10,10));
        hBox5.setAlignment(Pos.CENTER);
        hBox5.getChildren().addAll(quantityLabel,quantityTextField);

        VBox newBookVbox=new VBox();
        newBookVbox.setSpacing(30);
        newBookVbox.setPadding(new Insets(10,10,10,10));
        newBookVbox.setAlignment(Pos.CENTER);
        newBookVbox.getChildren().addAll(introLable,hBox1,hBox2,hBox3,hBox4,hBox5,addBookButton,returnButton);

        Scene addBookScene=new Scene(newBookVbox,800,600);
        addBookScene.getStylesheets().add("file:library.css");
        window.setScene(addBookScene);
        window.show();

        addBookButton.setOnAction(e->{
            name =nameTextField.getText();
            genre=genreTextField.getText();
            author=authorTextField.getText();
            publisher=publisherTextField.getText();
            quantity_string=quantityTextField.getText();

            int ishandled=HandleEmptyText(name,genre,author,publisher,quantity_string);
            if (ishandled==1){
                quantity = Integer.parseInt(quantity_string);
                AddBook addBook = new AddBook();
                try {
                    addBook.addbook(name,genre,author,publisher,quantity);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });

    }

    int HandleEmptyText(String name,String genre,String author,String publisher,String quantity){
        int flag=1;
        if (name.equals("")||genre.equals("")||author.equals("")||publisher.equals("")||quantity.equals("")){
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
