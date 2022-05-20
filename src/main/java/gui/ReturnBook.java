package main.java.gui;

import DB.RegisterReturn;
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

public class ReturnBook {
    int userID;
    int bookID;



    public void returnBook(Stage window, Scene previous) {
        Label introLable = new Label("Register a return");
        Label userIdLable = new Label("user Id");
        Label bookIdLable = new Label("book Id");

        TextField userIdTextField = new TextField("");
        TextField bookIdTextField = new TextField("");

        Button registerReturnButton = new Button("register");
        Button returnButton = new Button("return");

        HBox hBox1 =new HBox();
        hBox1.setSpacing(30);
        hBox1.setPadding(new Insets(10,10,10,10));
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(userIdLable,userIdTextField);

        HBox hBox2 =new HBox();
        hBox2.setSpacing(30);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(bookIdLable,bookIdTextField);

        VBox registerReturnVbox = new VBox();
        registerReturnVbox.setSpacing(30);
        registerReturnVbox.setPadding(new Insets(10, 10, 10, 10));
        registerReturnVbox.setAlignment(Pos.CENTER);
        registerReturnVbox.getChildren().addAll(introLable,hBox1,hBox2, registerReturnButton, returnButton);


        Scene registerReturnScene = new Scene(registerReturnVbox, 800, 600);
        registerReturnScene.getStylesheets().add("file:library.css");
        window.setScene(registerReturnScene);
        window.show();

        RegisterReturn registerReturn = new RegisterReturn();
        registerReturnButton.setOnAction(e -> {
            //todo
            String userID_string;
            String bookID_string;
            userID_string = userIdTextField.getText();
            bookID_string = bookIdTextField.getText();
            int ishandled=HandleEmptyText(userID_string,bookID_string);
            if (ishandled==1){
                userID = Integer.parseInt(userID_string);
                bookID = Integer.parseInt(bookID_string);
                try {
                    registerReturn.registerReturn(userID,bookID);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        returnButton.setOnAction(e -> {
            window.setScene(previous);
        });

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
    int HandleEmptyText(String userid,String bookid){
        int flag=1;
        if (userid.equals("")||bookid.equals("")){
            errorMessage();
            flag=0;

        }
        return flag;
    }
}


