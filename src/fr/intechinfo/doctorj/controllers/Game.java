package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import fr.intechinfo.doctorj.model.validators.ValidatorMessage;
import fr.intechinfo.doctorj.model.validators.ValidatorMessageElement;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.RSyntaxTextAreaUtils;
import fr.intechinfo.doctorj.views.customControls.GameDialog;
import fr.intechinfo.doctorj.views.customControls.SimpleDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the Game view
 */
public class Game extends AbstractController implements Initializable {
    @FXML private AnchorPane codeArea;
    @FXML private SwingNode swingNode;
    @FXML private ListView<Text> listExec;
    @FXML private Tab execTab;
    @FXML private Tab scenarioTab;
    @FXML private WebView webViewHelp;
    @FXML private Label lblScenario;
    @FXML private Label lblCurStoryStep;
    @FXML private MediaView gameMediaView;
    @FXML private StackPane messageVideo;
    @FXML private Label lblMessageVideo;

    private ObservableList<Text> listExecElements;
    private RSyntaxTextArea codeTextArea;
    private MediaPlayer gameMediaPlayer;

    public Game(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messageVideo.setVisible(false);

        listExecElements = FXCollections.observableArrayList();
        listExec.setItems(listExecElements);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        createSwingContent();

        fillGameView();

        PlayIntroStory();
    }

    private void createSwingContent() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                codeTextArea = new RSyntaxTextArea(20, 50);
                codeTextArea.setCodeFoldingEnabled(true);
                codeTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

                RTextScrollPane sp = new RTextScrollPane(codeTextArea);
                sp.setIconRowHeaderEnabled(true);

                LanguageSupportFactory lsf = LanguageSupportFactory.get();
                JavaLanguageSupport support = (JavaLanguageSupport) lsf.getSupportFor(SyntaxConstants.SYNTAX_STYLE_JAVA);

                try {
                    support.getJarManager().addCurrentJreClassFileSource();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                lsf.register(codeTextArea);

                swingNode.setContent(sp);
                RSyntaxTextAreaUtils.fixKeyboardIssues(codeTextArea, codeArea);
            }
        });
    }

    @FXML protected void onClickBtnHome(MouseEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }

    @FXML protected void onClickBtnLevel(MouseEvent event) {
        SelectLevel controller = new SelectLevel(getMainWindow(), "selectLevel");
        controller.show("Sélection d'un niveau");
    }

    @FXML protected void onClickBtnRun(MouseEvent event) throws IOException {
        String data = codeTextArea.getText();
        String strPath = Paths.getStoriesPath();
        String shortName = DoctorJ.getCurrentGameContext().getCurrentStory().getShortName();
        String userFileName = DoctorJ.getCurrentGameContext().getCurrentStep().getUserFileName();

        // Writes the file
        String saveFile = strPath + "/" + shortName + "/" + userFileName + ".java";
        FileUtils.writeStringToFile(new File(saveFile), data);

        // Clear output
        listExecElements.clear();

        ValidatorMessage m = SyntaxValidator.check(saveFile);

        addElementsToListExec(m.getMessage());

        if(m.isValid()) {
            ValidatorMessage m2 = TestValidator.check(shortName, userFileName);

            if(m2.isValid()) {
                GameDialog.showDialog("Etape réussie !", null);
                NextStep();
            }
            else {
                PlayBadEnd();
            }

            addElementsToListExec(m2.getMessage());
        }

        // Jump to tab
        execTab.getTabPane().getSelectionModel().select(execTab);
    }

    private void addElementsToListExec(List<ValidatorMessageElement> elements) {
        for(ValidatorMessageElement m : elements) {
            Text t = new Text(m.getElement());
            t.setStyle(m.getCode().getStyle());
            listExecElements.add(t);
        }
    }

    private void fillGameView() {
        lblCurStoryStep.setText(DoctorJ.getCurrentGameContext().getCurrentStory().getTitle() + " > " + DoctorJ.getCurrentGameContext().getCurrentStep().getTitle());

        WebEngine he = webViewHelp.getEngine();
        he.loadContent(DoctorJ.getCurrentGameContext().getCurrentStep().getHelp());

        lblScenario.setText(DoctorJ.getCurrentGameContext().getCurrentStep().getDirection());

        // Jump to tab
        scenarioTab.getTabPane().getSelectionModel().select(scenarioTab);
    }

    private void PlayVideo(String videoFileName, boolean loop, Runnable callback) {
        String shortNameStory = DoctorJ.getCurrentGameContext().getCurrentStory().getShortName();
        String userFileName = DoctorJ.getCurrentGameContext().getCurrentStep().getUserFileName();

        File f = new File(Paths.getStoriesPath() + "/" + shortNameStory + "/medias/" + videoFileName);
        Media m = new Media(f.toURI().toString());

        gameMediaPlayer = new MediaPlayer(m);
        gameMediaView.setMediaPlayer(gameMediaPlayer);

        gameMediaPlayer.play();

        if(loop) {
            gameMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }

        if(callback != null) {
            gameMediaPlayer.setOnEndOfMedia(callback);
        }

    }

    private void PlayIntroStory() {
        messageVideo.setVisible(false);

        String videoFileName = DoctorJ.getCurrentGameContext().getCurrentStory().getVideoStart();
        PlayVideo(videoFileName, false, new Runnable() {
            @Override
            public void run() {
                PlayIntroStep();
            }
        });
    }

    private void PlayIntroStep() {
        messageVideo.setVisible(false);

        String videoFileName = DoctorJ.getCurrentGameContext().getCurrentStep().getVideoStart();

        PlayVideo(videoFileName, false, new Runnable() {
            @Override
            public void run() {
                PlayLoopStep();
            }
        });
    }

    private void PlayLoopStep() {
        lblMessageVideo.setText("AUSCULTATION EN COURS...");
        messageVideo.setVisible(true);

        String videoFileName = DoctorJ.getCurrentGameContext().getCurrentStep().getVideoLoop();

        PlayVideo(videoFileName, true, null);
    }

    private void PlayGoodEnd() {
        lblMessageVideo.setText("LE PATIENT EST GUERI !");
        messageVideo.setVisible(true);

        String videoFileName = DoctorJ.getCurrentGameContext().getCurrentStory().getVideoGoodEnd();

        PlayVideo(videoFileName, false, new Runnable() {
            @Override
            public void run() {
                GameDialog.showDialog("Vous avez terminé ce niveau !", new Runnable() {
                    @Override
                    public void run() {
                        SelectLevel controller = new SelectLevel(getMainWindow(), "selectLevel");
                        controller.show("Sélection d'un niveau");
                    }
                });
            }
        });
    }

    private void PlayBadEnd() {
        lblMessageVideo.setText("LE PATIENT EST MORT !");
        messageVideo.setVisible(true);

        String videoFileName = DoctorJ.getCurrentGameContext().getCurrentStory().getVideoBadEnd();

        PlayVideo(videoFileName, false, new Runnable() {
            @Override
            public void run() {
                PlayLoopStep();
            }
        });
    }

    private void NextStep() {
        if(DoctorJ.getCurrentGameContext().goToNextStep()) {
            fillGameView();
            PlayIntroStep();
        }
        else {
            PlayGoodEnd();
        }
    }
}