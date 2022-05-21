package GUI;

import DB.UserVerification;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.sql.SQLException;

public class Login {
    private String userName;
    private String userPassword;
    int verified=0;

    ErrorMessages errorMessages = new ErrorMessages();

    public void launchLogin(Stage window , Scene previousScene){

        Label userNameLable=new Label("username");
        Label userPasswordLable=new Label("password");

        TextField userNameTextfield =new TextField("");
        PasswordField userPasswordTextfield =new PasswordField();

        HBox hBox1=new HBox();
        hBox1.setSpacing(30);
        hBox1.setPadding(new Insets(10,10,10,10));
        hBox1.setAlignment(Pos.CENTER);
        hBox1.getChildren().addAll(userNameLable,userNameTextfield);

        HBox hBox2=new HBox();
        hBox2.setSpacing(30);
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(userPasswordLable,userPasswordTextfield);

        Button userLoginButton =new Button("login");


        RadioButton userRadioButton =new RadioButton("user");
        RadioButton LibrarianRadioButton =new RadioButton("Librarian");
        RadioButton AdminRadioButton =new RadioButton("Admin");

        ToggleGroup RadioButtonToggleGroup =new ToggleGroup();
        userRadioButton.setSelected(true);

        userRadioButton.setToggleGroup(RadioButtonToggleGroup);
        LibrarianRadioButton.setToggleGroup(RadioButtonToggleGroup);
        AdminRadioButton.setToggleGroup(RadioButtonToggleGroup);

        HBox radioButtonHBox=new HBox();
        radioButtonHBox.setSpacing(30);
        radioButtonHBox.setPadding(new Insets(10,10,10,10));
        radioButtonHBox.setAlignment(Pos.CENTER);
        radioButtonHBox.getChildren().addAll(userRadioButton,LibrarianRadioButton,AdminRadioButton);


        Button createAccountButton= new Button("dont have an account?");
        Button returnButton=new Button("return");

        VBox userVbox =new VBox();
        userVbox.setSpacing(10);
        userVbox.setPadding(new Insets(10,10,10,10));
        userVbox.setAlignment(Pos.CENTER);
        userVbox.getChildren().addAll(hBox1,hBox2,radioButtonHBox,userLoginButton,createAccountButton,returnButton);

        Scene userLoginScene =new Scene(userVbox,800,600);
        userLoginScene.getStylesheets().add("file:library.css");
        window.setScene(userLoginScene);
        window.setMaximized(true);
        window.show();

        //........................................................//
        userLoginButton.setOnAction(e->{
            userName =userNameTextfield.getText();
            userPassword =userPasswordTextfield.getText();
            if (userRadioButton.isSelected()){
                //todo user verification
                try {
                    verifyUserLogin(userName, userPassword,window,userLoginScene);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (LibrarianRadioButton.isSelected()){
                try {
                    verifyLibrarianLogin(userName,userPassword,window,userLoginScene);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else if (AdminRadioButton.isSelected()){
                verifyAdminLogin(userName,userPassword,window,userLoginScene);
            }

        });
        createAccountButton.setOnAction(e->{
            UserInterface userInterface = new UserInterface();
            userInterface.newUser(window, userLoginScene, 1);
        });
        returnButton.setOnAction(e->{
            window.setScene(previousScene);
        });

    }
    public void verifyUserLogin(String userName , String password, Stage window, Scene userLoginScene) throws SQLException {
        //Todo verification
        UserVerification userVerification = new UserVerification();

        verified = userVerification.verifyUser(userName, password, 1);

        if(verified==1){
            UserDashboard userDashboard=new UserDashboard();
            userDashboard.launchUserDashboard(window,userLoginScene,userName);
        }else{

            errorMessages.errorMessage("Incorrect username or password");

        }
    }
    public void verifyLibrarianLogin(String userName , String password, Stage window, Scene userLoginScene) throws SQLException {
        //Todo verification

        UserVerification userVerification = new UserVerification();
        verified = userVerification.verifyUser(userName, password, 2);

        if(verified==1){
            LibrarianDashboard librarianDashboard=new LibrarianDashboard();
            librarianDashboard.launchLibrarianDashboard(window,userLoginScene);
        }else{

            errorMessages.errorMessage("Incorrect username or password");
        }


    }
    public void verifyAdminLogin(String userName , String password, Stage window, Scene userLoginScene){
        //Todo verification
        if(userName.equals("admin123")&&password.equals("admin123")){
            AdminDashboard adminDashboard=new AdminDashboard();
            adminDashboard.launchAdminDashboard(window,userLoginScene);
        }
        else {
            errorMessages.errorMessage("Incorrect username or password");
        }

    }
}
