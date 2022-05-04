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

public class LibrarianLogin {
    private String librarianEmail;
    private String librarianPassword;

    public void launchLibrarianLogin(Stage window , Scene previousScene){


        Label introLable=new Label("Librarian Login");
        Label librarianEmailLable=new Label("email");
        Label LibrarianPasswordLable=new Label("password");

        TextField librarianEmailTextfield =new TextField();
        PasswordField librarianPasswordTextfield =new PasswordField();

        Button librarianLoginButton =new Button("login");
        Button returnButton=new Button("return");

        VBox LibrarianVbox =new VBox();
        LibrarianVbox.setSpacing(30);
        LibrarianVbox.setPadding(new Insets(10,10,10,10));
        LibrarianVbox.setAlignment(Pos.CENTER);
        LibrarianVbox.getChildren().addAll(introLable,librarianEmailLable,librarianEmailTextfield,LibrarianPasswordLable,librarianPasswordTextfield,librarianLoginButton,returnButton);

        Scene librarianLoginScene =new Scene(LibrarianVbox,800,600);
        librarianLoginScene.getStylesheets().add("file:library.css");
        window.setScene(librarianLoginScene);
        window.show();

        //........................................................//
        librarianLoginButton.setOnAction(e->{
            librarianEmail=librarianEmailTextfield.getText();
            librarianPassword=librarianPasswordTextfield.getText();
            verifyLogin(librarianEmail,librarianPassword,window,librarianLoginScene);
        });
        returnButton.setOnAction(e->{
            window.setScene(previousScene);
        });

    }
    public void verifyLogin(String email ,String password,Stage window,Scene librarianLoginScene){
        //Todo verification
        librarianDashboard librarianDashboard=new librarianDashboard();
        librarianDashboard.launchLibrarianDashboard(window,librarianLoginScene);
    }
}
