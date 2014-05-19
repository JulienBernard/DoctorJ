package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.model.Storyline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class GeneratorGame extends Generator implements Initializable {
    @FXML private TextField gameNameField;
    @FXML private TextArea gamePitchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void newFile(ActionEvent actionEvent) {
    }

    public void loadFile(ActionEvent actionEvent) {
        System.out.println(getStory().getName());
        System.out.println(getStory().getPitch());
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
        getStory().setName(this.gameNameField.getText());
        getStory().setPitch(this.gamePitchField.getText());
        System.out.println(getStory().getName());
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void addChapter(ActionEvent actionEvent) {
    }

    public void addStep(ActionEvent actionEvent) {
    }

    public void toStep(ActionEvent actionEvent) {
    }

    public void toChapter(ActionEvent actionEvent) {
    }
}
