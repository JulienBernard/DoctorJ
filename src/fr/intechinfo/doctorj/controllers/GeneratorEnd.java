package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Storyline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the Generator view
 */
public class GeneratorEnd implements Initializable {

    private Storyline str = DoctorJ.getInstance().getStory();

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
        str.getChapters().add(new Chapter());
        int chapterSize = str.getChapters().size();

        // Add one to the current step position (on the list)
        str.setCurrentChapter(chapterSize - 1);
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter une chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1)", 800, 600);
    }

    public void end(ActionEvent actionEvent) {
    }

    public void previous(ActionEvent actionEvent) {
        str.setCurrentChapter(str.getChapters().size() - 1);
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1)", 800, 600);
    }
}
