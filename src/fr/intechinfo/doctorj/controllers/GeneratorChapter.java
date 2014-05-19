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

    public TextField getChapterNameField() {
        return chapterNameField;
    }

    public void setChapterNameField(TextField chapterNameField) {
        this.chapterNameField = chapterNameField;
    }
}
