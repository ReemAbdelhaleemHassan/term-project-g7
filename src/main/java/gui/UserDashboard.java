package GUI;

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

import java.sql.*;
import java.util.Date;

public class UserDashboard {
    String book_name;
    int isbn;
    String genre;
    String author;
    String publisher;
    int quantity;
    TableView<BookInfo> tableView =new TableView<>();
    public void launchUserDashboard(Stage window , Scene previous, String userName){
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
        window.setMaximized(true);
        window.show();
        //..................................................//
        viewAvailableBooksButton.setOnAction(e->{
            ViewBooks viewBooks = new ViewBooks();
            try {
                ResultSet result = viewBooks.viewBooks(1, "");

                ObservableList<BookInfo> books= FXCollections.observableArrayList();

                TableColumn<BookInfo,String> isbnColumn=new TableColumn<>("ISBN");
                isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

                TableColumn<BookInfo,String> nameColumn=new TableColumn<>("Name");
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("book_name"));

                TableColumn<BookInfo,String> genreColumn=new TableColumn<>("Genre");
                genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

                TableColumn<BookInfo,String> authorColumn=new TableColumn<>("author");
                authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

                TableColumn<BookInfo,String> publisherColumn=new TableColumn<>("publisher");
                publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

                TableColumn<BookInfo,String> quantityColumn=new TableColumn<>("quantity");
                quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                while(result.next()) {
                    book_name = result.getString("book_name");
                    isbn = result.getInt("isbn");
                    genre = result.getString("genre");
                    author = result.getString("author");
                    publisher = result.getString("publisher");
                    quantity = result.getInt("quantity");
                    books.add(new BookInfo(isbn, book_name, genre, author, publisher, quantity));
                }


                tableView.setItems(books);
                tableView.getColumns().addAll(isbnColumn,nameColumn,genreColumn,authorColumn,publisherColumn,quantityColumn);
                Button return2Button=new Button("return");
                return2Button.setOnAction(event -> {
                    window.setScene(userDashboardScene);
                });

                VBox ViewBooksVBox=new VBox();
                ViewBooksVBox.setSpacing(30);
                ViewBooksVBox.setPadding(new Insets(10,10,10,10));
                ViewBooksVBox.setAlignment(Pos.CENTER);
                ViewBooksVBox.getChildren().addAll(tableView,return2Button);

                Scene viewBooksScene=new Scene(ViewBooksVBox,800,600);
                viewBooksScene.getStylesheets().add("file:library.css");
                window.setScene(viewBooksScene);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        //........................................................//
        viewMyBorrowedBooksButton.setOnAction(e->{
            ViewBooks viewBooks = new ViewBooks();
            int isbn;
            int user_id;
            java.util.Date borrowDate;
            Date returnDate;

            TableView<BorrowedBookInfo> tableView =new TableView<>();
            try {
                ResultSet result = viewBooks.viewBooks(3, userName);

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
                    window.setScene(userDashboardScene);
                });

                VBox myBorrowedBooksVBox=new VBox();
                myBorrowedBooksVBox.setSpacing(30);
                myBorrowedBooksVBox.setPadding(new Insets(10,10,10,10));
                myBorrowedBooksVBox.setAlignment(Pos.CENTER);
                myBorrowedBooksVBox.getChildren().addAll(tableView,return2Button);

                Scene myBorrowedBookScene=new Scene(myBorrowedBooksVBox,800,600);
                myBorrowedBookScene.getStylesheets().add("file:library.css");
                window.setScene(myBorrowedBookScene);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });

    }
}
