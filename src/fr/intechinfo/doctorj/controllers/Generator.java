package fr.intechinfo.doctorj.controllers;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import fr.intechinfo.doctorj.DoctorJ;


/**
 * Controller for the Generator view
 */
public class Generator extends VBox implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void newFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Chapitres", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
    }

    public void closeFile(ActionEvent actionEvent) {
    }

    public void saveFile(ActionEvent actionEvent) {
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void addChapter(ActionEvent actionEvent) {
    }

    public void addStep(ActionEvent actionEvent) {
    }

    public void toStep(ActionEvent actionEvent) {
    }

    public void toChapter(ActionEvent actionEvent) {
    }
}
