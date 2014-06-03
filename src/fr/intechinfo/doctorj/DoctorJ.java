package fr.intechinfo.doctorj;

import fr.intechinfo.doctorj.model.validators.TestValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point of the program. Can switch between different scenes in the main window.
 * The running instance of the class is accessible globally with the static method "getInstance()".
 */
public class DoctorJ extends Application {

    private static DoctorJ instance;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    /**
     * First method called by the platform.
     */
    public void start(Stage primaryStage) {
        instance = this;
        this.primaryStage = primaryStage;
        changeScene("home", "Doctor J - Accueil", 800, 600);
    }

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

        System.out.println(TestValidator.check("story1", "Step1").getMessage());
    }

    /**
     * Gets the current instance of the application.
     * @return A DoctorJ object, the current instance of the application.
     */
    public static DoctorJ getInstance() {
        return instance;
    }
}
