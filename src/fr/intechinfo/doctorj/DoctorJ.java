package fr.intechinfo.doctorj;

import fr.intechinfo.doctorj.controllers.Home;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorJ extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainWindow) throws IOException {
        // TODO : LEGACY
        instance = this; this.primaryStage = mainWindow;

        Home home = new Home(mainWindow, "home");
        home.show("Accueil", 800, 600);

        mainWindow.show();
    }

    ////////////////////////////////// TODO : LEGACY //////////////////////////////////
    private static DoctorJ instance;
    private Stage primaryStage;

    /**
     * Gets the primary stage.
     * @return A Stage object containing the current scene.
     */
    public Stage getPrimaryStage(){
        return primaryStage;
    }

    /**
     * Changes the current scene of the primary stage.
     * @param view The name of the view, without the extension .fxml (e.g. : "home").
     * @param title The title of the window.
     * @param width The width of the window.
     * @param height The height of the window.
     */
    public void changeScene(String view, String title, double width, double height) {
        BorderPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("views/" + view + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, width, height);

        scene.getStylesheets().add(getClass().getResource("views/styles/global.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("views/styles/" + view + ".css").toExternalForm());

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * Gets the current instance of the application.
     * @return A DoctorJ object, the current instance of the application.
     */
    public static DoctorJ getInstance() {
        return instance;
    }
    ///////////////////////////////////////////////////////////////////////////////////
}
