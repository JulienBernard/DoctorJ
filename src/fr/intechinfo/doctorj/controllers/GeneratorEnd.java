package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;


/**
 * Controller for the Generator view
 */
public class GeneratorEnd implements Initializable {
    private Storyline str;
    @FXML private Label resultCheck;

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

    public void checkStory(ActionEvent actionEvent) {
        String result = "";
        if( str.getName().isEmpty() )
            result += "Champ vide : Le nom de votre histoire n'est pas renseigné !\n";
        if( str.getPitch().isEmpty() )
            result += "Champ vide : La description de votre histoire n'est pas renseignée !\n";
        if( str.getTestFile().isEmpty() )
            result += "Champ vide : Le nom du fichier de test de votre histoire n'est pas renseigné !\n";
        if( str.getChapters().isEmpty() )
            result += "Pas de chapitre : Il n'y a aucun chapitre à votre histoire !\n";

        for( int i = 0 ; i < str.getChapters().size() ; i++ )
        {
            if( str.getChapters().get(i).getTitle().isEmpty() )
                result += "Champ vide : Le titre d'un des chapitres ("+i+1+") n'est pas renseigné !\n";
            if( str.getChapters().get(i).getDescription().isEmpty() )
                result += "Champ vide : La description d'un des chapitres ("+i+1+") n'est pas renseignée !\n";

            for( int j = 0 ; j < str.getChapters().get(i).getSteps().size() ; j++ )
            {
                if( str.getChapters().get(i).getSteps().get(j).getTitle().isEmpty() )
                    result += "Champ vide : Le titre d'une des étapes ("+j+1+") de l'un de vos chapitres ("+i+1+") n'est pas renseigné !\n";
                if( str.getChapters().get(i).getSteps().get(j).getHelp().isEmpty() )
                    result += "Champ vide : L'aide d'une des étapes ("+j+1+") de l'un de vos chapitres ("+i+1+") n'est pas renseignée !\n";
                if( str.getChapters().get(i).getSteps().get(j).getHint().isEmpty() )
                    result += "Champ vide : Les astuces d'une des étapes ("+j+1+") de l'un de vos chapitres ("+i+1+") ne sont pas renseignées !\n";
                if( str.getChapters().get(i).getSteps().get(j).getDirection().isEmpty() )
                    result += "Champ vide : Les instructions d'une des étapes ("+j+1+") de l'un de vos chapitres ("+i+1+") ne sont pas renseignées !\n";
                if( str.getChapters().get(i).getSteps().get(j).getFunction().isEmpty() )
                    result += "Champ vide : Le nom de la fonction de test d'une des étapes ("+j+1+") de l'un de vos chapitres ("+i+1+") n'est pas renseigné !\n";
                else {

                }
                if( str.getChapters().get(i).getSteps().get(j).getImage().isEmpty() )
                    result += "Champ vide : L'image d'une des étapes ("+j+1+") de l'un de vos chapitres ("+i+1+") n'est pas renseignée !\n";
            }
        }
        if( result.isEmpty() )
            result = "Votre histoire semble correct. Retournez au menu pour y jouer des maintenant !\n";
        resultCheck.setText(result);
    }
}
