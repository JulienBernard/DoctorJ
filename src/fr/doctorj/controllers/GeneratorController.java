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

import java.awt.*;
import java.io.IOException;
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
        jsonReader JsonReader = new jsonReader( "" );
        listReturn = JsonReader.readFile(parser);
    }

    /**
     * Initialize the components from this view.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        storyNameField.setText(listReturn.get(0).get("storyName"));
        storyPitchField.setText(listReturn.get(0).get("storyPitch"));
        chapterNameField.setText(listReturn.get(1).get("chapterName"));
        chapterPitchField.setText(listReturn.get(1).get("chapterPitch"));
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
        /* Create Json file */
        System.out.println("Fonctionnalité à venir");
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
