package fr.doctorj.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {
    @FXML
    private Button generatorButton;
    private Button btnRun;
    private ImageView chat;

    @FXML protected void handleGeneratorButton(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../screens/generator.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DoctorJ - Imaginez votre propre histoire");
            stage.setScene(new Scene(root));
            stage.show();

            //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void handlePlayButton(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../screens/gameView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DoctorJ - TITLE STORY");
            stage.setScene(new Scene(root));
            stage.show();

            //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();

            TranslateTransition transition = createTransition(chat);

            btnRun.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    transition.play();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    TranslateTransition createTransition(ImageView image) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(5000));
        transition.setToX(0.5);
        transition.setCycleCount(3);
        transition.setAutoReverse(true);
        return transition;
    }
}
