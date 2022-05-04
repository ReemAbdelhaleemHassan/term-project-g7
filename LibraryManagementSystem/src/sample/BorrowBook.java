package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;

public class BorrowBook {
    int userID;
    int bookID;
    Timestamp borrowDate;
    Timestamp returnDate;

    public void borrow(Stage window,Scene previous){
        Label introLable =new Label("register a borrow");
        Label userIdLable =new Label("user Id");
        Label bookIdLable =new Label("book Id");

        TextField userIdTextField =new TextField();
        TextField bookIdTextField =new TextField();

        Button registerBorrowButton =new Button("register");
        Button returnButton =new Button("return");

        VBox registerBorrowVbox=new VBox();
        registerBorrowVbox.setSpacing(30);
        registerBorrowVbox.setPadding(new Insets(10,10,10,10));
        registerBorrowVbox.setAlignment(Pos.CENTER);
        registerBorrowVbox.getChildren().addAll(introLable,userIdLable,userIdTextField,bookIdLable,bookIdTextField,registerBorrowButton,returnButton);



        Scene registerBorrowScene=new Scene(registerBorrowVbox,800,600);
        registerBorrowScene.getStylesheets().add("file:library.css");
        window.setScene(registerBorrowScene);
        window.show();

        registerBorrowButton.setOnAction(e->{
            //todo
            userID=Integer.parseInt(userIdTextField.getText());
            bookID=Integer.parseInt(bookIdTextField.getText());


        });

        returnButton.setOnAction(e->{
            window.setScene(previous);
        });




    }
}
