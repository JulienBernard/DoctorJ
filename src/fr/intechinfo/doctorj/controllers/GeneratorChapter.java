package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Storyline;
import fr.intechinfo.doctorj.model.Chapter;
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
    @FXML private TextField chapterTitleField;
    @FXML private TextArea chapterDescriptionField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int size = DoctorJ.getInstance().getStory().getChapters().size();
        int id = DoctorJ.getInstance().getStory().getCurrentChapter();
        if( size != 0 )
        {
            Chapter chap = DoctorJ.getInstance().getStory().getChapters().get(id);
            this.chapterTitleField.setText(chap.getTitle());
            this.chapterDescriptionField.setText(chap.getDescription());
        }
    }

    public void newFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().setStory(new Storyline());
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        int size = DoctorJ.getInstance().getStory().getChapters().size();
        int id = DoctorJ.getInstance().getStory().getCurrentChapter();
        if( size != 0 )
        {
            Chapter chap = DoctorJ.getInstance().getStory().getChapters().get(id);
            this.chapterTitleField.setText(chap.getTitle());
            this.chapterDescriptionField.setText(chap.getDescription());
        }
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
        int id = DoctorJ.getInstance().getStory().getCurrentChapter();
        int size = DoctorJ.getInstance().getStory().getChapters().size();

        if( size == 0 ) {
            DoctorJ.getInstance().getStory().getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        } else if( DoctorJ.getInstance().getStory().getChapters().get(size - 1).getId() == id ) {
            DoctorJ.getInstance().getStory().getChapters().get(id).setTitle(this.chapterTitleField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(id).setDescription(this.chapterDescriptionField.getText());
        } else {
            DoctorJ.getInstance().getStory().getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        }
    }

    public void saveFile() {
        int id = DoctorJ.getInstance().getStory().getCurrentChapter();
        int size = DoctorJ.getInstance().getStory().getChapters().size();

        if( size == 0 ) {
            DoctorJ.getInstance().getStory().getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        } else if( DoctorJ.getInstance().getStory().getChapters().get(size - 1).getId() == id ) {
            DoctorJ.getInstance().getStory().getChapters().get(id).setTitle(this.chapterTitleField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(id).setDescription(this.chapterDescriptionField.getText());
        } else {
            DoctorJ.getInstance().getStory().getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        }
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void previous(ActionEvent actionEvent) {
        saveFile();
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Ajouter un chapitre", 800, 600);
    }

    public void next(ActionEvent actionEvent) {
        saveFile();
        DoctorJ.getInstance().changeScene("generatorStep", "Doctor J - Ajouter une étape", 800, 600);
    }
}
