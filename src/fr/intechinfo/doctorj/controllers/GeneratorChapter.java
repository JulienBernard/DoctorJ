package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Storyline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class GeneratorChapter extends Generator implements Initializable {
    @FXML private TextField chapterNameField;
    @FXML private TextArea chapterPitchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void newFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().setStory(new Storyline());
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
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

    public void previous(ActionEvent actionEvent) {
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Ajouter un chapitre", 800, 600);
    }

    public void next(ActionEvent actionEvent) {
    }
}
