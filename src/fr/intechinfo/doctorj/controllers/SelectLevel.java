package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandre on 18/06/2014.
 */
public class SelectLevel implements Initializable {
    @FXML private Button btnPlayAnnie;
    @FXML private Button btnPlayJeanne;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML protected void onClickBtnAnnie(ActionEvent event) {
        DoctorJ.getInstance().changeScene("game", "Doctor J - Annie et la découverte de Java", 800, 600);
    }

    @FXML protected void onClickBtnJeanne(ActionEvent event) {
        DoctorJ.getInstance().changeScene("game", "Doctor J - Jeanne et la découverte de l'orienté objet", 800, 600);
    }
}
