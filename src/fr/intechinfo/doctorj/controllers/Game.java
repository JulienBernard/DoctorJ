package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Storyline;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML private Label lblHelp;
    @FXML private Label lblHint;
    @FXML private Label lblChapterName;
    @FXML private Label lblChapterDescription;

    public static final String VID_URL = "file:./src/fr/intechinfo/doctorj/assets/test.mp4";

    public Game(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    public Game(Stage mainWindow, String viewName, Storyline story) {
        super(mainWindow, viewName, story);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Data select for the first chapter / first step
        getStory().setCurrentChapter(0);
        int currentChapter = getStory().getCurrentChapter();
        getStory().getChapters().get(currentChapter).setCurrentStep(0);
        int currentStep = getStory().getChapters().get(currentChapter).getCurrentStep();

        updateData();
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

        if( errorList.isEmpty() ) {
            errors += "Win";
            errorArea.setText(errors);
            setCurrentStepPlus();
            updateData();
        }
    }

    private Chapter getCurrentChapter() {
        return getStory().getChapters().get(getStory().getCurrentChapter());
    }

    private void setCurrentChapterPlus() {
        getStory().setCurrentChapter(getStory().getCurrentChapter() + 1);
    }

    private Step getCurrentStep() {
        Chapter chap = getCurrentChapter();
        return getCurrentChapter().getSteps().get(chap.getCurrentStep());
    }

    private void setCurrentStepPlus() {
        Chapter chap = getCurrentChapter();
        chap.setCurrentStep(chap.getCurrentStep() + 1);
    }

    private void updateData() {
        this.lblChapterName.setText( getCurrentChapter().getTitle() );
        this.lblChapterDescription.setText( getCurrentChapter().getDescription() );
        this.lblHelp.setText( getCurrentStep().getHelp() );
        this.lblHint.setText( getCurrentStep().getHint() );
    }
}
