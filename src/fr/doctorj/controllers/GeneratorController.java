package fr.doctorj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.parser.JSONParser;
import fr.doctorj.models.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.List;
import java.util.ResourceBundle;

public class GeneratorController implements Initializable {
    JSONParser parser = new JSONParser();
    public String strReturn;
    public List<Map<String, String>> listReturn;

    @FXML public VBox generatorWindow;
    @FXML public Label str;
    @FXML public AnchorPane storyStep;
    @FXML public AnchorPane chapterStep;
    @FXML public TextField storyNameField;
    @FXML public TextField chapterNameField;
    @FXML public TextArea storyPitchField;
    @FXML public TextArea chapterPitchField;

    public GeneratorController() {
    }

    /**
     * Initialize the components from this view.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storyStep.setVisible(false);
        chapterStep.setVisible(false);
        str.setVisible(true);
        str.setText("Choissisez une option ...");
    }

    public void newFile(ActionEvent actionEvent) {
        storyStep.setVisible(true);
        chapterStep.setVisible(false);
        str.setVisible(false);

        storyNameField.setText("");
        storyPitchField.setText("");
        chapterNameField.setText("");
        chapterPitchField.setText("");
    }

    public void loadFile(ActionEvent actionEvent) {
        storyStep.setVisible(true);
        chapterStep.setVisible(false);
        str.setVisible(false);

        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = dialogue.getSelectedFile();
                jsonReader JsonReader = new jsonReader( file.getPath() );
                listReturn = JsonReader.readFile(parser);

                if( listReturn.get(0).get("error") != null ) {
                    storyStep.setVisible(false);
                    chapterStep.setVisible(false);
                    str.setVisible(true);
                    str.setText(listReturn.get(0).get("error"));
                }
                else {
                    storyNameField.setText(listReturn.get(1).get("storyName"));
                    storyPitchField.setText(listReturn.get(1).get("storyPitch"));
                    chapterNameField.setText(listReturn.get(2).get("chapterName"));
                    chapterPitchField.setText(listReturn.get(2).get("chapterPitch"));
                }
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(e.getMessage());
            }
            return;
        }
    }

    public void toChapter(ActionEvent actionEvent) {
        storyStep.setVisible(false);
        chapterStep.setVisible(true);
        str.setVisible(false);
    }

    public void toStory(ActionEvent actionEvent) {
        storyStep.setVisible(true);
        chapterStep.setVisible(false);
        str.setVisible(false);
    }

    public void saveFile(ActionEvent actionEvent) {
        storyStep.setVisible(true);
        chapterStep.setVisible(false);
        str.setVisible(false);

        JFileChooser dialogue = new JFileChooser(new File("."));
        PrintWriter sortie;
        File file;

        if (dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = dialogue.getSelectedFile();
                jsonWriter JsonWriter = new jsonWriter( file.getPath() );

                if( JsonWriter.saveFile() ) {
                    System.out.println("Save done.");
                }
                else {
                    System.out.println("Wait, an error was catch: good location?");
                }
            } catch (NullPointerException e) {
                System.out.println(e);
            }
            return;
        }
    }

    public void closeFile(ActionEvent actionEvent) {
        storyStep.setVisible(false);
        chapterStep.setVisible(false);
        str.setVisible(true);
    }

    @FXML public void quit(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../samples/generator.fxml"));
            //hide this current window
            str.getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
