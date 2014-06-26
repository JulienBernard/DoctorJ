package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.model.ApplicationContext;
import fr.intechinfo.doctorj.model.RSyntaxTextAreaUtils;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import fr.intechinfo.doctorj.model.validators.ValidatorMessage;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
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
import java.util.ResourceBundle;

/**
 * Controller for the Game view
 */
public class Game extends AbstractController implements Initializable {
    @FXML private TextArea errorArea;
    @FXML private Button btnHome;
    @FXML private VBox vBoxCode;
    private RSyntaxTextArea codeArea;

    public Game(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingNode swingNode = new SwingNode();
        vBoxCode.getChildren().add(swingNode);

        createSwingContent(swingNode);
    }

    private void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                codeArea = new RSyntaxTextArea(100, 50);
                codeArea.setCodeFoldingEnabled(true);
                codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

                RTextScrollPane sp = new RTextScrollPane(codeArea);
                sp.setIconRowHeaderEnabled(true);

                LanguageSupportFactory lsf = LanguageSupportFactory.get();
                JavaLanguageSupport support = (JavaLanguageSupport) lsf.getSupportFor(SyntaxConstants.SYNTAX_STYLE_JAVA);

                try {
                    support.getJarManager().addCurrentJreClassFileSource();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                lsf.register(codeArea);

                swingNode.setContent(sp);
                RSyntaxTextAreaUtils.fixKeyboardIssues(codeArea, vBoxCode);
            }
        });
    }

    @FXML protected void onClickBtnHome(ActionEvent event) {
        Home home = new Home(getMainWindow(), "home");
        home.show("Accueil");
    }

    @FXML protected void handleExecuteButton(ActionEvent event) throws IOException {
        String data = codeArea.getText();
        String strPath = ApplicationContext.getInstance().getStoriesPath();
        String curStory = ApplicationContext.getInstance().getCurrentGameContext().getCurrentStory().getShortName();
        String curStep = ApplicationContext.getInstance().getCurrentGameContext().getCurrentStep().getShortName();

        // Writes the file
        String saveFile = strPath + "/" + curStory + "/" + curStep + ".java";
        FileUtils.writeStringToFile(new File(saveFile), data);

        ValidatorMessage m = SyntaxValidator.check(saveFile);

        errorArea.setText(m.getMessage());

        if(m.isValid()) {
            ValidatorMessage m2 = TestValidator.check(curStory, curStep);
            errorArea.appendText("\n" + m2.getMessage());
        }
    }
}