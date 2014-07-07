package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.utils.Serialization;
import fr.intechinfo.doctorj.views.customControls.Dialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        Story str = Serialization.loadFile("C:/Users/Stage Brel/Desktop/Dev/Lourd/Java/DoctorJ/stories/histoire/story.drj");
        listLevel.getChildren().add(new fr.intechinfo.doctorj.views.customControls.SelectLevel(str));
    }

    @FXML protected void onClickBtnAnnie(ActionEvent event) {
        Game controller = new Game(getMainWindow(), "game");
        controller.show("Annie et la découverte de Java");
    }

    @FXML protected void onClickBtnJeanne(ActionEvent event) {
        Game controller = new Game(getMainWindow(), "game");
        controller.show("Jeanne et la découverte de l'orienté objet");
    }

    public void searchStories() {
        Dialog.showDialog("Sauvegarde effectuée avec succès !");
    }
}
