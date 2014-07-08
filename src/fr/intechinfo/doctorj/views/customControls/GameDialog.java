package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.DoctorJ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Alexandre on 08/07/2014.
 */
public class GameDialog  extends VBox {
    @FXML private Button btn;
    @FXML private Label lbl;
    private Runnable callback;

    public GameDialog(String lbl, Runnable callback) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gameDialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        getStylesheets().add(DoctorJ.class.getResource("views/styles/global.css").toExternalForm());
        getStylesheets().add(DoctorJ.class.getResource("views/styles/gameDialog.css").toExternalForm());

        this.lbl.setText(lbl);
        this.callback = callback;
    }

    @FXML protected void onClickBtn(ActionEvent event) {
        callback.run();
        btn.getScene().getWindow().hide();
    }

    public static void showDialog(String s, Runnable callback) {
        GameDialog d = new GameDialog(s, callback);
        Stage stage = new Stage();
        stage.setTitle("Information");
        Scene scene = new Scene(d);
        stage.setScene(scene);
        stage.getIcons().add(new Image(DoctorJ.class.getResourceAsStream("assets/minilogoico.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
