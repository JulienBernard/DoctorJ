package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Storyline;
import fr.intechinfo.doctorj.model.fileReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandre on 18/06/2014.
 */
public class SelectLevel extends AbstractController implements Initializable {
    @FXML private Button btnPlayAnnie;
    @FXML private Button btnPlayJeanne;

    public SelectLevel(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML protected void onClickBtnAnnie(ActionEvent event) {
        Storyline story = readStory("./stories/test.story");
        Game controller = new Game(getMainWindow(), "game", story);
        controller.show("Annie et la découverte de Java");
    }

    @FXML protected void onClickBtnJeanne(ActionEvent event) {
        Storyline story = readStory("./stories/test.story");
        Game controller = new Game(getMainWindow(), "game", story);
        controller.show("Jeanne et la découverte de l'orienté objet");
    }

    private Storyline readStory( String path ) {
        Storyline str = new Storyline();
            try {
                fileReader reader = new fileReader( path );
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
        return str;
    }
}
