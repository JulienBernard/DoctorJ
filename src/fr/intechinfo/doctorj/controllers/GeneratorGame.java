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

    private Storyline str = DoctorJ.getInstance().getStory();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gameNameField.setText(DoctorJ.getInstance().getStory().getName());
        this.gamePitchField.setText(DoctorJ.getInstance().getStory().getPitch());
    }

    public void newFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().setStory(new Storyline());
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        this.gameNameField.setText(DoctorJ.getInstance().getStory().getName());
        this.gamePitchField.setText(DoctorJ.getInstance().getStory().getPitch());
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().getStory().setName(this.gameNameField.getText());
        DoctorJ.getInstance().getStory().setPitch(this.gamePitchField.getText());
    }

    public void saveFile() {
        DoctorJ.getInstance().getStory().setName(this.gameNameField.getText());
        DoctorJ.getInstance().getStory().setPitch(this.gamePitchField.getText());
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void next(ActionEvent actionEvent) {
        saveFile();

        if( DoctorJ.getInstance().getStory().getChapters().size() == 0) {
            DoctorJ.getInstance().getStory().getChapters().add(new Chapter());
        }
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre", 800, 600);
    }
}
