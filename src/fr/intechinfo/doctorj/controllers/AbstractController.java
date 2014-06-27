package fr.intechinfo.doctorj.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Alexandre on 25/06/2014.
 */
public abstract class AbstractController {
    private Stage mainWindow;
    private Parent view;
    private String viewName;

    protected AbstractController() {
    }

    public AbstractController(Stage mainWindow, String viewName) {
        this.mainWindow = mainWindow;
        this.viewName = viewName;
    }

    public void show(String title) {
        show(title, mainWindow.getScene().getWidth(), mainWindow.getScene().getHeight());
    }

    public void show(String title, double width, double height) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/" + viewName + ".fxml"));
            loader.setController(this);

            view = loader.load();

            Scene scene = new Scene(view, width, height);

            scene.getStylesheets().add(getClass().getResource("../views/styles/global.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("../views/styles/" + viewName + ".css").toExternalForm());

            mainWindow.setTitle("DoctorJ - " + title);
            mainWindow.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getMainWindow() {
        return mainWindow;
    }

    public Parent getView() {
        return view;
    }

    public String getViewName() {
        return viewName;
    }
}
