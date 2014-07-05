package fr.intechinfo.doctorj.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class Generator extends AbstractController implements Initializable {
    @FXML MenuItem menuNewStory;
    @FXML MenuItem menuImportStory;
    @FXML MenuItem menuGoHome;
    @FXML ListView listStories;
    @FXML AnchorPane controlContainer;

    public Generator(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML protected void onClickMenuNewStory(ActionEvent event) {

    }

    @FXML protected void onClickMenuImportStory(ActionEvent event) {

    }

    @FXML protected void onClickMenuGoHome(ActionEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }
}
