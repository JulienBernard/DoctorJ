package fr.intechinfo.doctorj.controllers;

import com.sun.java.swing.plaf.motif.MotifTreeCellRenderer;
import fr.intechinfo.doctorj.model.ApplicationContext;
import fr.intechinfo.doctorj.model.RSyntaxTextAreaUtils;
import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import fr.intechinfo.doctorj.model.validators.ValidatorMessage;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fife.rsta.ac.LanguageSupport;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import sun.awt.IconInfo;

import javax.swing.*;
import java.awt.*;
import java.io.*;
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
        SwingNode swingNode = new SwingNode();
        vBoxCode.getChildren().add(swingNode);

        createSwingContent(swingNode);
    }

    private void createSwingContent(SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }

            JPanel cp = new JPanel(new BorderLayout());

            codeArea = new RSyntaxTextArea(30, 70);
            codeArea.setCodeFoldingEnabled(true);
            codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

            RTextScrollPane sp = new RTextScrollPane(codeArea);
            sp.setIconRowHeaderEnabled(true);

            cp.add(sp);

            swingNode.setContent(cp);

            CompletionProvider provider = RSyntaxTextAreaUtils.createCompletionProvider();

            AutoCompletion ac = new AutoCompletion(provider);
            ac.install(codeArea);

            RSyntaxTextAreaUtils.fixKeyboardIssues(codeArea, vBoxCode);

            // TODO : bugué
            LanguageSupportFactory.get().register(codeArea);
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

        SyntaxValidator syntaxValidator;
        ValidatorMessage m = SyntaxValidator.check(saveFile);

        errorArea.setText(m.getMessage());

        if(m.isValid()) {
            ValidatorMessage m2 = TestValidator.check(curStory, curStep);
            errorArea.appendText("\n" + m2.getMessage());
        }
    }
}
