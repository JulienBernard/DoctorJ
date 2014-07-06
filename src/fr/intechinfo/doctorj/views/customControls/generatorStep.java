package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.utils.RSyntaxTextAreaUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.io.IOException;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class GeneratorStep extends VBox {
    private Step step;
    private Story story;

    @FXML private Label lblStoryName;
    @FXML private Label lblStepName;

    @FXML private TextField stepName;
    @FXML private TextField userCodeName;
    @FXML private TextArea direction;
    @FXML private TextArea help;
    @FXML private TextField videoStart;
    @FXML private Button openVideoStart;
    @FXML private TextField videoLoop;
    @FXML private Button openVideoLoop;
    @FXML private Button btnSave;

    @FXML private Button btnSaveTest;
    @FXML private Button btnCompile;
    @FXML private SwingNode codeNode;
    @FXML private ListView<Text> listCode;

    private RSyntaxTextArea codeTextArea;

    public GeneratorStep(Step s, Story st) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generatorStep.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        step = s;
        story = st;

        setupCodeEditor();
        fillForm();
    }

    public void setupCodeEditor() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                codeTextArea = new RSyntaxTextArea(100, 50);
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

                codeNode.setContent(sp);
                RSyntaxTextAreaUtils.fixKeyboardIssues(codeTextArea, codeNode);
            }
        });
    }

    public void fillForm() {
        lblStoryName.setText(story.getTitle());
        lblStepName.setText(step.getTitle());
        stepName.setText(step.getTitle());
        userCodeName.setText(step.getUserFileName());
        direction.setText(step.getDirection());
        help.setText(step.getHelp());
        videoStart.setText(step.getVideoStart());
        videoLoop.setText(step.getVideoLoop());
    }

    public void changeStep() {
        story.setTitle(lblStoryName.getText());
        step.setTitle(lblStepName.getText());
        step.setTitle(stepName.getText());
        step.setUserFileName(userCodeName.getText());
        step.setDirection(direction.getText());
        step.setHelp(help.getText());
        step.setVideoStart(videoStart.getText());
        step.setVideoLoop(videoLoop.getText());
    }

    @FXML protected void onClickOpenVideoStart(ActionEvent event) {

    }

    @FXML protected void onClickOpenVideoLoop(ActionEvent event) {

    }

    @FXML protected void onClickBtnSaveTest(ActionEvent event) {

    }

    @FXML protected void onClickBtnGenerateSkeleton(ActionEvent event) {

    }

    @FXML protected void onClickBtnSave(ActionEvent event) {
        changeStep();
    }

    @FXML protected void onClickBtnCompile(ActionEvent event) {

    }
}