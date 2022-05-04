package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
            Librarian librarian=new Librarian();
            librarian.newLibrarian(window,adminDashboardScene);
        });
        viewLibrarianButton.setOnAction(e->{
            //Todo
        });
        deleteLibrarianButton.setOnAction(e->{
            //Todo
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });
    }
}