package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import fr.intechinfo.doctorj.model.validators.ValidatorMessage;
import fr.intechinfo.doctorj.model.validators.ValidatorMessageElement;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.RSyntaxTextAreaUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    @FXML private Button btnHome;
    @FXML private AnchorPane codeArea;
    @FXML private SwingNode swingNode;
    @FXML private ListView<Text> listExec;
    @FXML private Tab execTab;

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

    @FXML protected void onClickBtnHome(ActionEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }

    @FXML protected void onClickBtnRun(ActionEvent event) throws IOException {
        String data = codeTextArea.getText();
        String strPath = Paths.getStoriesPath();
        String curStory = DoctorJ.getCurrentGameContext().getCurrentStory().getShortName();
        String userFileName = DoctorJ.getCurrentGameContext().getCurrentStep().getUserFileName();

        // Writes the file
        String saveFile = strPath + "/" + curStory + "/" + userFileName + ".java";
        FileUtils.writeStringToFile(new File(saveFile), data);

        // Clear output
        listExecElements.clear();

        ValidatorMessage m = SyntaxValidator.check(saveFile);

        addElementsToListExec(m.getMessage());

        if(m.isValid()) {
            ValidatorMessage m2 = TestValidator.check(curStory, userFileName);

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
}