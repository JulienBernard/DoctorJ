package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.controllers.Game;
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
    private Story str;

    public SelectLevel(Story str) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("selectLevel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.str = str;
        this.title.setText(str.getTitle());
        this.desc.setText(str.getDescription());
    }

    @FXML protected void onClickBtn(ActionEvent event) {
        Game g = new Game((Stage) getScene().getWindow(), "game");
        DoctorJ.getCurrentGameContext().setCurrentStory(str);
        g.show(str.getTitle());
    }
}
