package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.model.validators.*;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.RSyntaxTextAreaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.rsta.ac.java.JavaLanguageSupport;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

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
    private ObservableList<Text> listExecElements;

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

        listExecElements = FXCollections.observableArrayList();
        listCode.setItems(listExecElements);

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
                    support.getJarManager().addClassFileSource(new File(Paths.getAppPath() + "/junit-4.11.jar"));
                    support.getJarManager().addClassFileSource(new File(Paths.getAppPath() + "/hamcrest-core-1.3.jar"));
                    support.getJarManager().addClassFileSource(new File(Paths.getAppPath() + "/DoctorJ.jar"));
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                lsf.register(codeTextArea);

                codeNode.setContent(sp);
                RSyntaxTextAreaUtils.fixKeyboardIssues(codeTextArea, codeNode);

                String fileName = step.getUserFileName() + "Test.java";
                File f = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + fileName);

                if(f.exists()) {
                    try {
                        String code = FileUtils.readFileToString(f);
                        codeTextArea.setText(code);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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

    public boolean changeStep(boolean notify) {
        if(userCodeName.getText() != null) {

            // Step name changed
            if(userCodeName.getText() != step.getUserFileName()) {

                // Video start
                if(step.getVideoStart() != null) {
                    File f = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/medias/" + step.getVideoStart());
                    String ext = FilenameUtils.getExtension(f.getAbsolutePath());
                    String fileName = "videoStartStep" + userCodeName.getText() + "." + ext;
                    File nf = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/medias/" + fileName);

                    f.renameTo(nf);
                    videoStart.setText(fileName);
                }

                // Video loop
                if(step.getVideoLoop() != null) {
                    File f = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/medias/" + step.getVideoLoop());
                    String ext = FilenameUtils.getExtension(f.getAbsolutePath());

                    String fileName = "videoLoopStep" + userCodeName.getText() + "." + ext;
                    File nf = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/medias/" + fileName);

                    f.renameTo(nf);
                    videoLoop.setText(fileName);
                }

                // User code test
                File oldUserCode = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + step.getUserFileName() + "Test.java");
                File newUserCode = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + userCodeName.getText() + "Test.java");
                oldUserCode.renameTo(newUserCode);

                // User code test compiled
                File oldUserCodeCompiled = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + step.getUserFileName() + "Test.class");
                File newUserCodeCompiled = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + userCodeName.getText() + "Test.class");
                oldUserCodeCompiled.renameTo(newUserCodeCompiled);
            }

            story.setTitle(lblStoryName.getText());
            step.setTitle(lblStepName.getText());
            step.setTitle(stepName.getText());
            step.setUserFileName(userCodeName.getText());
            step.setDirection(direction.getText());
            step.setHelp(help.getText());
            step.setVideoStart(videoStart.getText());
            step.setVideoLoop(videoLoop.getText());

            if(notify) Dialog.showDialog("L'étape a bien été modifiée !");
            fillForm();
            return true;
        }
        else {
            Dialog.showDialog("Au moins un des champs est vide !");
            return false;
        }
    }

    @FXML protected void onClickOpenVideoStart(ActionEvent event) {
        if(changeStep(false)) {
            FileChooser f = new FileChooser();
            f.setTitle("Sélectionnez la vidéo de départ :");
            File video = f.showOpenDialog(getScene().getWindow());

            if (video != null) {
                String ext = FilenameUtils.getExtension(video.getAbsolutePath());
                String fileName = "videoStartStep" + step.getUserFileName() + "." + ext;
                File newFile = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/medias/" + fileName);

                try {
                    FileUtils.copyFile(video, newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                videoStart.setText(fileName);
            }
        }
    }

    @FXML protected void onClickOpenVideoLoop(ActionEvent event) {
        if(changeStep(false)) {
            FileChooser f = new FileChooser();
            f.setTitle("Sélectionnez la vidéo en boucle :");
            File video = f.showOpenDialog(getScene().getWindow());

            if (video != null) {
                String ext = FilenameUtils.getExtension(video.getAbsolutePath());
                String fileName = "videoLoopStep" + step.getUserFileName() + "." + ext;
                File newFile = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/medias/" + fileName);

                try {
                    FileUtils.copyFile(video, newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                videoLoop.setText(fileName);
            }
        }
    }

    @FXML protected void onClickBtnSaveTest(ActionEvent event) {
        String fileName = step.getUserFileName() + "Test.java";
        File f = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + fileName);

        try {
            FileUtils.writeStringToFile(f, codeTextArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onClickBtnGenerateSkeleton(ActionEvent event) {
        try {
            String template = FileUtils.readFileToString(new File(DoctorJ.class.getResource("assets/TemplateTestCase.txt").toURI()));
            template = template.replace("<<STORYNAME>>", story.getShortName());
            template = template.replace("<<STEPNAME>>", step.getUserFileName());

            codeTextArea.setText(template);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void onClickBtnSave(ActionEvent event) {
        changeStep(true);
    }

    @FXML protected void onClickBtnCompile(ActionEvent event) {
        onClickBtnSaveTest(null);

        listExecElements.clear();

        String fileName = step.getUserFileName() + "Test.java";
        File f = new File(Paths.getStoriesPath() + "/" + story.getShortName() + "/tests/" + fileName);

        ValidatorMessage m = SyntaxValidator.check(f.getAbsolutePath());
        if(m.isValid()) {
            m.getMessage().add(new ValidatorMessageElement("Le code a été compilé avec succès !", ValidatorConstants.OK));
        }

        addElementsToListExec(m.getMessage());
    }

    private void addElementsToListExec(List<ValidatorMessageElement> elements) {
        for(ValidatorMessageElement m : elements) {
            Text t = new Text(m.getElement());
            t.setStyle(m.getCode().getStyle());
            listExecElements.add(t);
        }
    }
}