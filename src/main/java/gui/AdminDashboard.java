package src.main.java.gui;

import DB.DeleteLibrarian;
import DB.ViewLibrarian;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.main.java.gui.UserInterface;

import java.sql.SQLException;

public class AdminDashboard {
    public void launchAdminDashboard(Stage window ,Scene previous){

        Button addLibrarianButton =new Button("Add Librarian");
        Button viewLibrarianButton =new Button("View Librarian");
        Button deleteLibrarianButton =new Button("Delete Librarian");
        Button returnButton =new Button("return");

        VBox adminDashboardVbox=new VBox();
        adminDashboardVbox.setSpacing(30);
        adminDashboardVbox.setPadding(new Insets(10,10,10,10));
        adminDashboardVbox.setAlignment(Pos.CENTER);
        adminDashboardVbox.getChildren().addAll(addLibrarianButton,viewLibrarianButton,deleteLibrarianButton,returnButton);

        Scene adminDashboardScene =new Scene(adminDashboardVbox,800,600);
        adminDashboardScene.getStylesheets().add("file:library.css");
        window.setScene(adminDashboardScene);
        window.show();
        //........................................//
        addLibrarianButton.setOnAction(e->{
            UserInterface userInterface = new UserInterface();
            userInterface.newUser(window, adminDashboardScene, 2);
        });
        viewLibrarianButton.setOnAction(e->{
            //Todo
            ViewLibrarian viewLibrarian = new ViewLibrarian();
            try {
                viewLibrarian.viewLibrarian();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteLibrarianButton.setOnAction(e->{
            //Todo
            DeleteLibrarian deleteLibrarian = new DeleteLibrarian();
            try {
                //todo input username
                deleteLibrarian.deleteLibrarian("user_name");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });
    }
}