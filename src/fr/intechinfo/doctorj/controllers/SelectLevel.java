package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.Serialization;
import fr.intechinfo.doctorj.views.customControls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
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
        String[] dir = new File(Paths.getStoriesPath()).list();

        for (int i=0; i<dir.length; i++)
        {
            File f = new File(Paths.getStoriesPath()+"/"+dir[i]+"/story.drj");
            if(f.exists()) {
                Story str = Serialization.loadFile(f.getAbsolutePath());

                if(str.getSteps().size() > 0) {
                    listLevel.getChildren().add(new fr.intechinfo.doctorj.views.customControls.SelectLevel(str));
                }
            }
        }
    }

    @FXML protected void GoToHome(MouseEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }
}
