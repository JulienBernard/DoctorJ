package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.model.Story;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Alexandre on 06/07/2014.
 */
public class SelectLevel extends VBox{
    @FXML private Button btn;
    @FXML private Label title;
    @FXML private Label desc;

    public SelectLevel(Story str) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectLevel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.title.setText(str.getTitle());
        this.desc.setText(str.getDescription());
    }

    @FXML protected void onClickBtn(ActionEvent event) {

    }
}
