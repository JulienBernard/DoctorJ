package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

/**
 * Controller for the Home view
 */
public class GeneratorChapter extends Generator implements Initializable {
    @FXML private TextField chapterTitleField;
    @FXML private TextArea chapterDescriptionField;

    private Storyline str;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        str = Storyline.getInstance();

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
        str.resetStoryline();
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        str.resetStoryline();
        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                JSONParser parser = new JSONParser();

                file = dialogue.getSelectedFile();
                fileReader reader = new fileReader( file.getPath() );

                Storyline story = reader.readStory();
                str.setName(story.getName());
                str.setTestFile(story.getTestFile());
                str.setPitch(story.getPitch());
                str.setChapters(story.getChapters());
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(e.getStackTrace());
            }
        }
        if( str.getName() != null )
        {
            DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
        }
    }

    public void saveFile(ActionEvent actionEvent) {
        int id = str.getCurrentChapter();
        str.getChapters().get(id).setTitle(this.chapterTitleField.getText());
        str.getChapters().get(id).setDescription(this.chapterDescriptionField.getText());
    }

    public void saveFile() {
        int id = str.getCurrentChapter();
        str.getChapters().get(id).setTitle(this.chapterTitleField.getText());
        str.getChapters().get(id).setDescription(this.chapterDescriptionField.getText());
    }

    public void quit(ActionEvent actionEvent) {
        int result = JOptionPane.showConfirmDialog(new Frame(), "Retourner au menu ?");
        if( result == 0 )
            DoctorJ.getInstance().changeScene("home", "Doctor J - Menu", 800, 600);
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

    public void saveFullFile(ActionEvent actionEvent) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        System.out.println("Save ...");

        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = dialogue.getSelectedFile();
                fileWriter JsonWriter = new fileWriter( file.getPath() );

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

    public void about(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fonctionnalité à venir.");
    }

    public void faq(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fonctionnalité à venir.");
    }
}
