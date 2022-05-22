package GUI;

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
    boolean true_info = false;
    ErrorMessages errorMessages = new ErrorMessages();



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

                    true_info = registerReturn.isInfoCorrect(userID,bookID);
                    if(true_info){
                        registerReturn.registerReturn(userID,bookID);
                    }else{
                        errorMessages.errorMessage("Please enter right information");
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });

        returnButton.setOnAction(e -> {
            window.setScene(previous);
        });

    }


    int HandleEmptyText(String userid,String bookid){
        int flag=1;
        if (userid.equals("")||bookid.equals("")){
            errorMessages.errorMessage("please enter all information required");

            flag=0;

        }
        return flag;
    }
}


