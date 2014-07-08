package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import fr.intechinfo.doctorj.model.validators.ValidatorMessage;
import fr.intechinfo.doctorj.model.validators.ValidatorMessageElement;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.RSyntaxTextAreaUtils;
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
    @FXML private WebView webViewHelp;
    @FXML private Label lblScenario;

    private ObservableList<Text> listExecElements;
    private RSyntaxTextArea codeTextArea;

    public Game(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listExecElements = FXCollections.observableArrayList();
        listExec.setItems(listExecElements);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        createSwingContent();

        IntroStory();
        fillTabs();
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
        Story currentStory = DoctorJ.getCurrentGameContext().getCurrentStory();
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
                // TODO : Donc là, le code de l'utilisateur est valide !
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

    private void fillTabs() {
        WebEngine he = webViewHelp.getEngine();
        he.loadContent(DoctorJ.getCurrentGameContext().getCurrentStep().getHelp());

        lblScenario.setText(DoctorJ.getCurrentGameContext().getCurrentStep().getDirection());
    }

    private void IntroStory() {

    }

    private void IntroStep() {

    }

    private void PlayStep() {

    }

    private void LoseStep() {

    }

    private void WinStep() {

    }

    private void WinStory() {

    }
}