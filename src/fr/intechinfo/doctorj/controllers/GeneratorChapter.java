package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class GeneratorChapter implements Initializable {
    @FXML private TextField chapterNameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("load generator chapter");
    }

    public void newFile(ActionEvent actionEvent) {
    }

    public void loadFile(ActionEvent actionEvent) {
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
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
