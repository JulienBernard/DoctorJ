package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Storyline;
import fr.intechinfo.doctorj.model.jsonReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import fr.intechinfo.doctorj.DoctorJ;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
                jsonReader reader = new jsonReader( file.getPath() );

                /* Il y a que'seule story */
                Map<String, String> story = reader.readStory(parser);
                str.setName(story.get("storyName"));
                str.setPitch(story.get("storyPitch"));

                /* Il y a plusieurs chapitres par story */
                List<Map<String, String>> chapters = reader.readChapters(parser);
                List<List<Map<String, String>>> steps = reader.readSteps(parser);
                for( int i = 0 ; i < chapters.size() ; i++ ) {
                    str.getChapters().add(new Chapter(chapters.get(i).get("chapterName"), chapters.get(i).get("chapterPitch"), i));

                    /* Il y a plusieurs steps par chapitres */
                    for( int j = 0 ; j < steps.size() ; j++ ) {
                        str.getChapters().get(i).getSteps().add(new Step(steps.get(i).get(j).get("stepTitle"),
                                steps.get(i).get(j).get("stepHelp"),
                                steps.get(i).get(j).get("stepDirection"),
                                steps.get(i).get(j).get("stepHint"),
                                steps.get(i).get(j).get("stepImage"),
                                steps.get(i).get(j).get("stepFunction"),
                                j));
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(e.getStackTrace());
            }
        }
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void closeFile(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fermeture impossible : il faut créer un nouveau fichier !");
    }

    public void saveFile(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Sauvegarde impossible : il faut créer un nouveau fichier !");
    }

    public void quit(ActionEvent actionEvent) {
        int result = JOptionPane.showConfirmDialog(new Frame(), "Retourner au menu ?");
        if( result == 0 )
            DoctorJ.getInstance().changeScene("home", "Doctor J - Menu", 800, 600);
    }

    public void saveFullFile(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Sauvegarde impossible : il faut créer un nouveau fichier !");
    }
}
