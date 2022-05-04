package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class LibraryUser extends User{
    int userID;
 public void newUser(Stage window,Scene previous){
     Label introLable=new Label("create account");
     Label nameLable=new Label("Name");
     Label ageLable=new Label("Age");
     Label phoneNumberLable=new Label("PhoneNumber");
     Label addressLable=new Label("Address");
     Label cityLable=new Label("city");
     Label emailLable=new Label("email");
     Label passwordLable=new Label("password");

     TextField nameTextField =new TextField();
     TextField ageTextField =new TextField();
     TextField phoneNumberTextField =new TextField();
     TextField addressTextField =new TextField();
     TextField cityTextField =new TextField();
     TextField emailTextField =new TextField();
     PasswordField passwordTextfield=new PasswordField();

     Button createButton =new Button("Create");
     Button returnButton=new Button("return");

     VBox createUserAccountVbox=new VBox();
     createUserAccountVbox.setSpacing(30);
     createUserAccountVbox.setPadding(new Insets(10,10,10,10));
     createUserAccountVbox.setAlignment(Pos.CENTER);
     createUserAccountVbox.getChildren().addAll(introLable,nameLable,nameTextField,ageLable,ageTextField,phoneNumberLable,phoneNumberTextField,addressLable,addressTextField,cityLable,cityTextField,emailLable,emailTextField,passwordLable,passwordTextfield,createButton,returnButton);

     Scene createUserAccountScene=new Scene(createUserAccountVbox,800,600);
     createUserAccountScene.getStylesheets().add("file:library.css");
     window.setScene(createUserAccountScene);
     window.show();

     createButton.setOnAction(e->{
         Random rand =new Random();
         userID=rand.nextInt(1000)+1; //todo create unique ID
         name=nameTextField.getText();
         age=Integer.parseInt(ageTextField.getText());
         phoneNumber=Integer.parseInt(phoneNumberTextField.getText());
         address=addressTextField.getText();
         city=cityTextField.getText();
         email=emailTextField.getText();
         password=passwordTextfield.getText();

         //todo:save in database

     });
     returnButton.setOnAction(e->{
         window.setScene(previous);
     });


 }
}
