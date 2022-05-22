package GUI;

import DB.AddBook;
import DB.AddUser;
import DB.RegisterBorrow;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.SQLException;

import java.time.LocalDate;

public class BorrowBook {
    int userID;
    int bookID;
    LocalDate borrowDate;
    LocalDate returnDate;

    boolean existed_info = false;
    boolean qauntity_info = false;
    boolean book_exists = false;
    boolean user_exists = false;
    ErrorMessages errorMessages = new ErrorMessages();

    public void borrow(Stage window,Scene previous){
        Label introLable =new Label("Register a borrow");
        Label userIdLable =new Label("user Id");
        Label bookIdLable =new Label("book Id");
        Label borrowDateLabel = new Label("borrow Date");
        Label returnDateLabel = new Label("return Date");

        TextField userIdTextField =new TextField();
        TextField bookIdTextField =new TextField();
        DatePicker borrowDatePicker = new DatePicker();
        DatePicker returnDatePicker = new DatePicker();

        borrowDatePicker.setValue(LocalDate.now());
        returnDatePicker.setValue(LocalDate.now());



        Button registerBorrowButton =new Button("register");
        Button returnButton =new Button("return");

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

        HBox hBox3 =new HBox();
        hBox2.setSpacing(30);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(borrowDateLabel,borrowDatePicker);

        HBox hBox4 =new HBox();
        hBox2.setSpacing(30);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(returnDateLabel,returnDatePicker);

        VBox registerBorrowVbox=new VBox();
        registerBorrowVbox.setSpacing(30);
        registerBorrowVbox.setPadding(new Insets(10,10,10,10));
        registerBorrowVbox.setAlignment(Pos.CENTER);
        registerBorrowVbox.getChildren().addAll(introLable,hBox1,hBox2,hBox3,hBox4,registerBorrowButton,returnButton);



        Scene registerBorrowScene=new Scene(registerBorrowVbox,800,600);
        registerBorrowScene.getStylesheets().add("file:library.css");
        window.setScene(registerBorrowScene);
        window.setMaximized(true);
        window.show();

        RegisterBorrow registerBorrow = new RegisterBorrow();
        AddBook addBook = new AddBook();


        registerBorrowButton.setOnAction(e->{

            String userID_string=userIdTextField.getText();
            String bookID_string=bookIdTextField.getText();


            int ishandled=HandleEmptyText(userID_string,bookID_string);
            if (ishandled==1){
                userID=Integer.parseInt(userIdTextField.getText());
                bookID=Integer.parseInt(bookIdTextField.getText());
                borrowDate=borrowDatePicker.getValue();
                returnDate=returnDatePicker.getValue();
                if(borrowDate.compareTo(returnDate)>0){
                    errorMessages.errorMessage("Return date must be after borrow date");
                }
                try {

                    existed_info= registerBorrow.isInfoCorrect(userID,bookID);
                    qauntity_info= registerBorrow.isQuantity(bookID);
                    user_exists = registerBorrow.isUserInDB(userID);
                    book_exists = addBook.isBookInDB(bookID);
                    if(!existed_info && qauntity_info && user_exists && book_exists && borrowDate.compareTo(returnDate)<=0){
                        RegisterBorrow.register(userID,bookID,borrowDate,returnDate);
                    }else {
                        errorMessages.errorMessage("Please enter right information");
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        });

        returnButton.setOnAction(e->{
            window.setScene(previous);
        });

    }


    int HandleEmptyText(String userid,String bookid){
        int flag=1;
        if (userid.equals("")||bookid.equals("")){
            errorMessages.errorMessage("enter the required information");
            flag=0;
        }
        return flag;
    }
}