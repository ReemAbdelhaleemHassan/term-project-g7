package main.java.gui;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation {
    public void parallelTransition(Button button, Duration duration){

        TranslateTransition translateTransition =new TranslateTransition();
        translateTransition.setNode(button);
        translateTransition.setDuration(duration);
        translateTransition.setFromX(800);
        translateTransition.setToX(button.getTranslateX());
        translateTransition.setCycleCount(1);



        FadeTransition fadeTransition =new FadeTransition();
        fadeTransition.setNode(button);
        fadeTransition.setDuration(duration);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);

        ParallelTransition parallelTransition =new ParallelTransition(translateTransition,fadeTransition);
        parallelTransition.play();
    }
}
