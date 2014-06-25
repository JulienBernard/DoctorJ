package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        Game controller = new Game(getMainWindow(), "game");
        controller.show("Annie et la découverte de Java");
    }

    @FXML protected void onClickBtnJeanne(ActionEvent event) {
        Game controller = new Game(getMainWindow(), "game");
        controller.show("Jeanne et la découverte de l'orienté objet");
    }
}
