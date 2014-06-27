package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the Game view
 */
public class Game extends AbstractController implements Initializable {
    @FXML private TextArea codeArea;
    @FXML private TextArea errorArea;
    @FXML private Button btnHome;
    @FXML private MediaView background;

    public static final String VID_URL = "file:./src/fr/intechinfo/doctorj/assets/test.mp4";

    public Game(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media clip = new Media(VID_URL);
        MediaPlayer player = new MediaPlayer(clip);
        player.setAutoPlay(true);
        background.setMediaPlayer(player);

    }

    @FXML protected void onClickBtnHome(ActionEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }

    @FXML protected void handleExecuteButton(ActionEvent event) throws IOException {

        String data = codeArea.getText();

        FileUtils.writeStringToFile(new File("./stories/story1/Step1.java"), data);

        SyntaxValidator syntaxValidator;
        List<String> errorList = SyntaxValidator.check("./stories/story1/Step1.java");
        String errors = "";

        for (String anErrorList : errorList) {
            errors += anErrorList;
        }
        errorArea.setText(errors);

        if(errors.length() < 10 && codeArea.getText().length() > 6) {
            errors += TestValidator.check("story1", "Step1").getMessage();

            errorArea.setText(errors);
        }
    }
}
