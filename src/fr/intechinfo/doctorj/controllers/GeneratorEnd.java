package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;


/**
 * Controller for the Generator view
 */
public class GeneratorEnd implements Initializable {

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
                java.util.List<Map<String, String>> chapters = reader.readChapters(parser);
                java.util.List<java.util.List<Map<String, String>>> steps = reader.readSteps(parser);
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

    public void addChapter(ActionEvent actionEvent) {
        str.getChapters().add(new Chapter());
        int chapterSize = str.getChapters().size();
        str.setCurrentChapter(chapterSize - 1);
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter une chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1)", 800, 600);
    }

    public void saveQuit(ActionEvent actionEvent) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        System.out.println("Save ...");

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

    public void previous(ActionEvent actionEvent) {
        str.setCurrentChapter(str.getChapters().size() - 1);
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1)", 800, 600);
    }

    public void saveFullFile(ActionEvent actionEvent) {
        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        System.out.println("Save ...");

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

    public void about(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fonctionnalité à venir.");
    }

    public void faq(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(new Frame(), "Fonctionnalité à venir.");
    }
}
