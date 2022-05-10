package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserDashboard {
    public void launchUserDashboard(Stage window , Scene previous){
        Button viewAvailableBooksButton = new Button("Available Books");
        Button viewMyBorrowedBooksButton = new Button("My Borrowed Books");
        Button returnButton = new Button("return");

        VBox userDashboardVbox =new VBox();
        userDashboardVbox.setSpacing(30);
        userDashboardVbox.setPadding(new Insets(10,10,10,10));
        userDashboardVbox.setAlignment(Pos.CENTER);
        userDashboardVbox.getChildren().addAll(viewAvailableBooksButton,viewMyBorrowedBooksButton,returnButton);

        Scene userDashboardScene=new Scene(userDashboardVbox,800,600);
        userDashboardScene.getStylesheets().add("file:library.css");
        window.setScene(userDashboardScene);
        window.show();

        viewAvailableBooksButton.setOnAction(e->{
            ViewBooks viewBooks = new ViewBooks();
            try {
                viewBooks.viewBooks(1);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        viewMyBorrowedBooksButton.setOnAction(e->{
            ViewBooks viewBooks = new ViewBooks();
            try {
                viewBooks.viewBooks(2);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });

    }
}
