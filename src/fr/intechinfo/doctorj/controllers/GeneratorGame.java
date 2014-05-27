package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.Step;
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

    private Storyline str;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        str = Storyline.getInstance();

        this.gameNameField.setText(str.getName());
        this.gamePitchField.setText(str.getPitch());
    }

    public void newFile(ActionEvent actionEvent) {
        str.resetStoryline();
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        this.gameNameField.setText(str.getName());
        this.gamePitchField.setText(str.getPitch());
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
        str.setName(this.gameNameField.getText());
        str.setPitch(this.gamePitchField.getText());
    }

    public void saveFile() {
        str.setName(this.gameNameField.getText());
        str.setPitch(this.gamePitchField.getText());
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void next(ActionEvent actionEvent) {
        saveFile();

        if( str.getChapters().size() == 0) {
            str.getChapters().add(new Chapter());
        }
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre", 800, 600);
    }
}
