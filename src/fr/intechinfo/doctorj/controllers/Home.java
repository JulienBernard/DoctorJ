package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Storyline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class Home implements Initializable {
    @FXML Button btnNewGame;
    @FXML Button btnContinue;
    @FXML Button btnGenerator;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML protected void onClickBtnNewGame(ActionEvent event) {
        DoctorJ.getInstance().changeScene("game", "Doctor J - Le jeu", 800, 600);
    }

    @FXML protected void onClickBtnContinue(ActionEvent event) {
        DoctorJ.getInstance().changeScene("game", "Doctor J - Le jeu", 800, 600);
    }

    @FXML protected void onClickBtnGenerator(ActionEvent event) {
        DoctorJ.getInstance().changeScene("generator", "Doctor J - Générateur d'histoires", 800, 600);
    }
}
