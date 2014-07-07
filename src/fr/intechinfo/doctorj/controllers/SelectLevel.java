package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.Serialization;
import fr.intechinfo.doctorj.views.customControls.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandre on 18/06/2014.
 */
public class SelectLevel extends AbstractController implements Initializable {
    @FXML private VBox listLevel;

    public SelectLevel(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] dir = new java.io.File(Paths.getStoriesPath()).list( );
        for (int i=0; i<dir.length; i++)
        {
            Story str = Serialization.loadFile(Paths.getStoriesPath()+"/"+dir[i]+"/story.drj");
            listLevel.getChildren().add(new fr.intechinfo.doctorj.views.customControls.SelectLevel(str));
        }
    }

    @FXML protected void GoToHome(MouseEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }
}
