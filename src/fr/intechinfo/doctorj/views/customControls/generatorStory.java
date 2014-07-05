package fr.intechinfo.doctorj.views.customControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class GeneratorStory extends VBox {

    public GeneratorStory() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generatorStory.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}