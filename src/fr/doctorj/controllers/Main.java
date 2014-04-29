package fr.doctorj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import fr.doctorj.models.jsonReader;

import java.io.IOException;

public class Main {
    @FXML
    private Button generatorButton;

    @FXML protected void handleGeneratorButton(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../samples/generator.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DoctorJ - Imaginez votre propre histoire");
            stage.setScene(new Scene(root));
            stage.show();

            //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();

            new Generator();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void handlePlayButton(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../samples/gameView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("DoctorJ - TITLE STORY");
            stage.setScene(new Scene(root));
            stage.show();

            //hide this current window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
