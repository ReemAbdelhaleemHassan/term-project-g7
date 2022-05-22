package GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ErrorMessages {
    public void errorMessage(String message){
        Stage errorWindow =new Stage();
        Label errorLable=new Label(message);
        Button okButton =new Button("OK");

        VBox errorVBox=new VBox();
        errorVBox.setSpacing(30);
        errorVBox.setAlignment(Pos.CENTER);
        errorVBox.getChildren().addAll(errorLable,okButton);

        okButton.setOnAction(e->{
            errorWindow.close();
        });

        Scene errorScene =new Scene(errorVBox,700,150);
        errorScene.getStylesheets().addAll("file:library.css");

        errorWindow.setScene(errorScene);
        errorWindow.setTitle("error");
        errorWindow.showAndWait();
    }
}
