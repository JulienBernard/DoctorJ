package fr.intechinfo.doctorj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class Home extends AbstractController implements Initializable {
    @FXML Button btnNewGame;
    @FXML Button btnGenerator;

    public Home(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML protected void onClickBtnNewGame(ActionEvent event) {
        SelectLevel controller = new SelectLevel(getMainWindow(), "selectLevel");
        controller.show("Sélection d'un niveau");
    }

    @FXML protected void onClickBtnGenerator(ActionEvent event) {
        //TODO : Generator controller = new Generator(getMainWindow(), "generator");
        //controller.show("Générateur d'histoires");
    }
}
