package src.main.java.gui;

import DB.ViewBooks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



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

        //...................................................//
        addBookButton.setOnAction(e->{
            Book book=new Book();
            book.newBook(window,librarianDashboardScene);
        });
        //...................................................//
        viewBorrowedBooksButton.setOnAction(e->{
            ViewBooks viewBooks = new ViewBooks();
            //todo

            int isbn;
            int user_id;
            Date borrowDate;
            Date returnDate;

            TableView<BorrowedBookInfo> tableView =new TableView<>();
            try {
                ResultSet result = viewBooks.viewBooks(2, "");

                ObservableList<BorrowedBookInfo> books= FXCollections.observableArrayList();

                TableColumn<BorrowedBookInfo,String> isbnColumn=new TableColumn<>("ISBN");
                isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

                TableColumn<BorrowedBookInfo,String> user_idColumn=new TableColumn<>("user_id");
                user_idColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));

                TableColumn<BorrowedBookInfo,String> borrowDateColumn=new TableColumn<>("borrowDate");
                borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));

                TableColumn<BorrowedBookInfo,String> returnDateColumn=new TableColumn<>("returnDate");
                returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));


                while(result.next()) {
                    isbn = result.getInt("isbn");
                    user_id = result.getInt("user_id");
                    borrowDate = result.getDate("pick_up_date");
                    returnDate = result.getDate("return_date");

                    books.add(new BorrowedBookInfo(isbn, user_id, borrowDate, returnDate));
                }


                tableView.setItems(books);
                tableView.getColumns().addAll(isbnColumn, user_idColumn, borrowDateColumn, returnDateColumn);

                Button return2Button=new Button("return");
                return2Button.setOnAction(event -> {
                    window.setScene(librarianDashboardScene);
                });

                VBox ViewBooksVBox=new VBox();
                ViewBooksVBox.setSpacing(30);
                ViewBooksVBox.setPadding(new Insets(10,10,10,10));
                ViewBooksVBox.setAlignment(Pos.CENTER);
                ViewBooksVBox.getChildren().addAll(tableView,return2Button);

                Scene viewBorrowedBookScene=new Scene(ViewBooksVBox,800,600);
                window.setScene(viewBorrowedBookScene);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        //...................................................//
        registerABorrowButton.setOnAction(e->{
            BorrowBook borrowBook=new BorrowBook();
            borrowBook.borrow(window,librarianDashboardScene);
        });
        //...................................................//
        registerAReturnButton.setOnAction(e->{
            ReturnBook returnBook=new ReturnBook();
            returnBook.returnBook(window,librarianDashboardScene);
        });
        //...................................................//
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });
    }
}