package main.java.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibrarianDashboard {

    public void launchLibrarianDashboard(Stage window , Scene previous){
        Button addBookButton = new Button("Add book");
        Button viewBorrowedBooksButton = new Button("view borrowed books");
        Button registerABorrowButton = new Button("register a borrow");
        Button registerAReturnButton = new Button("register a return");
        Button returnButton = new Button("return");

        VBox librarianDashboardVbox =new VBox();
        librarianDashboardVbox.setSpacing(30);
        librarianDashboardVbox.setPadding(new Insets(10,10,10,10));
        librarianDashboardVbox.setAlignment(Pos.CENTER);
        librarianDashboardVbox.getChildren().addAll(addBookButton,viewBorrowedBooksButton,registerABorrowButton,registerAReturnButton,returnButton);

        Scene librarianDashboardScene =new Scene(librarianDashboardVbox,800,600);
        librarianDashboardScene.getStylesheets().add("file:library.css");
        window.setScene(librarianDashboardScene);
        window.show();

        addBookButton.setOnAction(e->{
            Book book=new Book();
            book.newBook(window,librarianDashboardScene);
        });
        viewBorrowedBooksButton.setOnAction(e->{
            //todo
        });
        registerABorrowButton.setOnAction(e->{
            BorrowBook borrowBook=new BorrowBook();
            borrowBook.borrow(window,librarianDashboardScene);
        });
        registerAReturnButton.setOnAction(e->{
            ReturnBook returnBook=new ReturnBook();
            returnBook.returnBook(window,librarianDashboardScene);
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });
    }
}