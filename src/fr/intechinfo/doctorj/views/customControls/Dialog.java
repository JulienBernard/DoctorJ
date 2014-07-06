package fr.intechinfo.doctorj.views.customControls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alexandre on 06/07/2014.
 */
public class Dialog extends VBox{
    @FXML private Button btn;
    @FXML private Label lbl;

    public Dialog(String lbl) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dialog.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.lbl.setText(lbl);
    }

    @FXML protected void onClickBtn(ActionEvent event) {
        btn.getScene().getWindow().hide();
    }

    public static void showDialog(String s) {
        Dialog d = new Dialog(s);
        Stage stage = new Stage();
        stage.setTitle("Information");
        Scene scene = new Scene(d);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
    }
}
