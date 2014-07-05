package fr.intechinfo.doctorj.views.customControls;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class GeneratorHome extends VBox {

    public GeneratorHome() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generatorHome.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this,0.0);
        AnchorPane.setLeftAnchor(this,0.0);
        AnchorPane.setRightAnchor(this,0.0);
    }
}
