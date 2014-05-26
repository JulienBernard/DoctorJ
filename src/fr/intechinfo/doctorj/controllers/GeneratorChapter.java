package fr.intechinfo.doctorj.controllers;

import com.sun.java.swing.plaf.windows.resources.windows;
import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Storyline;
import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.jsonWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class GeneratorChapter extends Generator implements Initializable {
    @FXML private TextField chapterTitleField;
    @FXML private TextArea chapterDescriptionField;

    private Storyline str = DoctorJ.getInstance().getStory();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int size = str.getChapters().size();
        int id = str.getCurrentChapter();
        if( size != 0 && id < size )
        {
            Chapter chap = str.getChapters().get(id);
            this.chapterTitleField.setText(chap.getTitle());
            this.chapterDescriptionField.setText(chap.getDescription());
        }
    }

    public void newFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().setStory(new Storyline());
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        int size = str.getChapters().size();
        int id = str.getCurrentChapter();
        if( size != 0 )
        {
            Chapter chap = str.getChapters().get(id);
            this.chapterTitleField.setText(chap.getTitle());
            this.chapterDescriptionField.setText(chap.getDescription());
        }
    }

    public void closeFile(ActionEvent actionEvent) {
        Stage popUp = new Stage();
        popUp.initModality(Modality.WINDOW_MODAL);
        popUp.setScene(new Scene(new Group(new Label("toto"))));
        popUp.show();
    }

    public void saveFile(ActionEvent actionEvent) {
        int id = str.getCurrentChapter();
        int size = str.getChapters().size();

        if( size == 0 ) {
            str.getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        } else if( str.getChapters().get(size - 1).getId() == id ) {
            str.getChapters().get(id).setTitle(this.chapterTitleField.getText());
            str.getChapters().get(id).setDescription(this.chapterDescriptionField.getText());
        } else {
            str.getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        }
    }

    public void saveFile() {
        int id = str.getCurrentChapter();
        int size = str.getChapters().size();

        if( size == 0 ) {
            str.getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        } else if( str.getChapters().get(size - 1).getId() == id ) {
            str.getChapters().get(id).setTitle(this.chapterTitleField.getText());
            str.getChapters().get(id).setDescription(this.chapterDescriptionField.getText());
        } else {
            str.getChapters().add(new Chapter(this.chapterTitleField.getText(), this.chapterDescriptionField.getText(), size));
        }
    }

    public void saveFullFile(ActionEvent actionEvent) {
        saveFile();

        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        System.out.println("... Saving file ...");

        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = dialogue.getSelectedFile();
                jsonWriter JsonWriter = new jsonWriter( file.getPath() );

                if( JsonWriter.saveFile(str) ) {
                    System.out.println("Save done.");
                }
                else {
                    System.out.println("Wait, an error was catch: good location?");
                }
            } catch (NullPointerException e) {
                System.out.println(e);
            }
        }
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void previous(ActionEvent actionEvent) {
        saveFile();

        int chapterSize = str.getChapters().size();
        if( str.getCurrentChapter() == 0 )
        {
            DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
        } else {
            str.setCurrentChapter(str.getCurrentChapter() - 1);
            DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1))", 800, 600);
        }
    }

    public void next(ActionEvent actionEvent) {
        saveFile();

        int idChap = str.getCurrentChapter();
        str.getChapters().get(idChap).setCurrentStep(0);
        if( str.getChapters().get(idChap).getSteps().size() == 0) {
            str.getChapters().get(idChap).getSteps().add(new Step());
        }
        DoctorJ.getInstance().changeScene("generatorStep", "Doctor J - Ajouter une étape (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1)", 800, 600);
    }
}
