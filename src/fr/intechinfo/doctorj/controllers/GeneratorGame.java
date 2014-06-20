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
public class GeneratorGame extends Generator implements Initializable {
    @FXML private TextField gameNameField;
    @FXML private TextField gameTestField;
    @FXML private TextArea gamePitchField;

    private Storyline str;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        str = Storyline.getInstance();

        this.gameNameField.setText(str.getName());
        this.gamePitchField.setText(str.getPitch());
        this.gameTestField.setText(str.getTestFile());
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
        str.setName(this.gameNameField.getText());
        str.setPitch(this.gamePitchField.getText());
        str.setTestFile(this.gameTestField.getText());
    }

    public void saveFile() {
        str.setName(this.gameNameField.getText());
        str.setPitch(this.gamePitchField.getText());
        str.setTestFile(this.gameTestField.getText());
    }

    public void quit(ActionEvent actionEvent) {
        int result = JOptionPane.showConfirmDialog(new Frame(), "Retourner au menu ?");
        if( result == 0 )
            DoctorJ.getInstance().changeScene("home", "Doctor J - Menu", 800, 600);
    }

    public void next(ActionEvent actionEvent) {
        saveFile();

        if( str.getChapters().size() == 0) {
            str.getChapters().add(new Chapter());
        }
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre", 800, 600);
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

    public void findFile(ActionEvent actionEvent) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        File file;

        str.resetStoryline();
        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = dialogue.getSelectedFile();
                gameTestField.setText(file.getName());
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(e.getStackTrace());
            }
        }
    }
}
