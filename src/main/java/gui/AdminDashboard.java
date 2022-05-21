package GUI;

import DB.AddUser;
import DB.DeleteLibrarian;
import DB.ViewLibrarian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDashboard {
    ErrorMessages errorMessages = new ErrorMessages();
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
        window.setMaximized(true);
        window.show();
        //........................................//
        addLibrarianButton.setOnAction(e->{
            UserInterface userInterface = new UserInterface();
            userInterface.newUser(window, adminDashboardScene, 2);
        });
        //......................................................//
        viewLibrarianButton.setOnAction(e->{
            //Todo
            ViewLibrarian viewLibrarian = new ViewLibrarian();
            try {
                TableView<Librarian> tableView =new TableView<>();
                ResultSet result=viewLibrarian.viewLibrarian();

                String name;
                int age;
                int phoneNumber;
                String address;
                String city;
                String username;
                String password;

                ObservableList<Librarian> librarians= FXCollections.observableArrayList();

                TableColumn<Librarian,String> nameColumn=new TableColumn<>("name");
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<Librarian,String> ageColumn=new TableColumn<>("Age");
                ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

                TableColumn<Librarian,String> PhonenumberColumn=new TableColumn<>("PhoneNumber ");
                PhonenumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

                TableColumn<Librarian,String> addressColumn=new TableColumn<>("address");
                addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

                TableColumn<Librarian,String> cityColumn=new TableColumn<>("city");
                cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

                TableColumn<Librarian,String> usernameColumn=new TableColumn<>("username");
                usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

                TableColumn<Librarian,String> passwordColumn=new TableColumn<>("password");
                passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                while(result.next()) {
                    name = result.getString("first_name");
                    age = result.getInt("age");
                    phoneNumber=result.getInt("phone_number");
                    address = result.getString("address");
                    city = result.getString("city");
                    username = result.getString("user_name");
                    password = result.getString("password");
                    librarians.add(new Librarian(name,age,phoneNumber,address,city,username,password));
                }


                tableView.setItems(librarians);
                tableView.getColumns().addAll(nameColumn,ageColumn,PhonenumberColumn,addressColumn,cityColumn,usernameColumn,passwordColumn);
                Button return2Button=new Button("return");
                return2Button.setOnAction(event -> {
                    window.setScene(adminDashboardScene);
                });
                VBox viewLibrarianVBox=new VBox();
                viewLibrarianVBox.setSpacing(30);
                viewLibrarianVBox.setPadding(new Insets(10,10,10,10));
                viewLibrarianVBox.setAlignment(Pos.CENTER);
                viewLibrarianVBox.getChildren().addAll(tableView,return2Button);
                Scene viewLibrarianscene=new Scene(viewLibrarianVBox,800,600);
                viewLibrarianscene.getStylesheets().add("file:library.css");
                window.setScene(viewLibrarianscene);


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        //......................................................//
        deleteLibrarianButton.setOnAction(e->{
            //Todo
            DeleteLibrarian deleteLibrarian = new DeleteLibrarian();

            //todo input username
            Label introLable=new Label("Delete Librarian");
            Label usernameLable=new Label("enter Librarian username you want to delete:");
            TextField usernameTextfield=new TextField("");
            Button deleteButton=new Button("Delete");
            Button return2Button=new Button("return");

            HBox deleteLibrarianHbox=new HBox();
            deleteLibrarianHbox.setAlignment(Pos.CENTER);
            deleteLibrarianHbox.setSpacing(30);
            deleteLibrarianHbox.getChildren().addAll(usernameLable,usernameTextfield);

            VBox deleteLibrarianVBox=new VBox();
            deleteLibrarianVBox.setSpacing(30);
            deleteLibrarianVBox.setPadding(new Insets(10,10,10,10));
            deleteLibrarianVBox.setAlignment(Pos.CENTER);

            deleteLibrarianVBox.getChildren().addAll(introLable,deleteLibrarianHbox,deleteButton,return2Button);
            Scene deleteLibrarianScene=new Scene(deleteLibrarianVBox,800,600);
            deleteLibrarianScene.getStylesheets().add("file:library.css");
            window.setScene(deleteLibrarianScene);
            return2Button.setOnAction(event -> {
                window.setScene(adminDashboardScene);
            });
            deleteButton.setOnAction(event->{
                AddUser addUser = new AddUser();
                boolean exists = false;
                String username = usernameTextfield.getText();
                try {
                    exists = addUser.isLibrarianInDB(username);
                    if(exists){
                        deleteLibrarian.deleteLibrarian(username);
                    }else{
                        errorMessages.errorMessage("Please enter existed librarian");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
        });
        //......................................................//
        returnButton.setOnAction(e->{
            window.setScene(previous);
        });
    }
}