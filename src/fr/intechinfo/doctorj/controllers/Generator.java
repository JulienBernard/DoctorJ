package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.model.Storyline;
import fr.intechinfo.doctorj.model.fileReader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import fr.intechinfo.doctorj.DoctorJ;
import org.json.simple.parser.JSONParser;

import javax.swing.*;


/**
 * Controller for the Generator view
 */
public class Generator implements Initializable {
    private Storyline str;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        str = Storyline.getInstance();
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
                str.setBackgroundFile(story.getBackgroundFile());
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

    public void quit(ActionEvent actionEvent) {
        int result = JOptionPane.showConfirmDialog(new Frame(), "Retourner au menu ?");
        if( result == 0 )
            DoctorJ.getInstance().changeScene("home", "Doctor J - Menu", 800, 600);
    }

    public void about(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fonctionnalité à venir.");
    }

    public void faq(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fonctionnalité à venir.");
    }
}
