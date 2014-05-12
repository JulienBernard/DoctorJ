package fr.intechinfo.doctorj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Generator view
 */
public class Generator extends VBox implements Initializable {
    @FXML private VBox generatorAnchor;
    @FXML private Button toChapter;
    @FXML private Button toStep;
    @FXML private Button addStep;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public Generator() {
    }

    public Generator(String sample) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sample));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void toChapter(ActionEvent actionEvent) {
        generatorAnchor.getChildren().remove(0,generatorAnchor.getChildren().size());
        generatorAnchor.getChildren().add(new Generator("../views/generatorChapter.fxml"));

        toChapter.setVisible(false);
        toStep.setVisible(true);
        addStep.setVisible(false);
    }

    public void toStep(ActionEvent actionEvent) {
        generatorAnchor.getChildren().remove(0,generatorAnchor.getChildren().size());
        generatorAnchor.getChildren().add(new Generator("../views/generatorStep.fxml"));

        toChapter.setVisible(false);
        toStep.setVisible(false);
        addStep.setVisible(true);
    }

    public void addStep(ActionEvent actionEvent) {
        generatorAnchor.getChildren().add(new Generator("../views/generatorStep.fxml"));
    }

    public void newFile(ActionEvent actionEvent) {
        generatorAnchor.getChildren().remove(0,generatorAnchor.getChildren().size());
        generatorAnchor.getChildren().add(new Generator("../views/generatorGame.fxml"));

        toChapter.setVisible(true);
        toStep.setVisible(false);
        addStep.setVisible(false);
    }

    public void loadFile(ActionEvent actionEvent) {
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
    }
}
