package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.model.ApplicationContext;
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
import org.fife.ui.autocomplete.*;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Game view
 */
public class Game extends AbstractController implements Initializable {
    @FXML private TextArea codeArea;
    @FXML private TextArea errorArea;
    @FXML private Button btnHome;
    @FXML private VBox vBoxCode;

    public Game(Stage mainWindow, String viewName) {
        super(mainWindow, viewName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SwingNode swingNode = new SwingNode();
        vBoxCode.getChildren().add(swingNode);

        createSwingContent(swingNode);
        
         
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JPanel cp = new JPanel(new BorderLayout());

                RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
                textArea.setCodeFoldingEnabled(true);

                RTextScrollPane sp = new RTextScrollPane(textArea);
                cp.add(sp);
                swingNode.setContent(cp);
                CompletionProvider provider = createCompletionProvider();
                AutoCompletion ac = new AutoCompletion(provider);
                ac.install(textArea);

            }


                private CompletionProvider createCompletionProvider() {

                    // A DefaultCompletionProvider is the simplest concrete implementation
                    // of CompletionProvider. This provider has no understanding of
                    // language semantics. It simply checks the text entered up to the
                    // caret position for a match against known completions. This is all
                    // that is needed in the majority of cases.
                    DefaultCompletionProvider provider = new DefaultCompletionProvider();

                    // Add completions for all Java keywords. A BasicCompletion is just
                    // a straightforward word completion.
                    provider.addCompletion(new BasicCompletion(provider, "abstract"));
                    provider.addCompletion(new BasicCompletion(provider, "assert"));
                    provider.addCompletion(new BasicCompletion(provider, "break"));
                    provider.addCompletion(new BasicCompletion(provider, "case"));
                    provider.addCompletion(new BasicCompletion(provider, "catch"));
                    provider.addCompletion(new BasicCompletion(provider, "class"));
                    provider.addCompletion(new BasicCompletion(provider, "const"));
                    provider.addCompletion(new BasicCompletion(provider, "continue"));
                    provider.addCompletion(new BasicCompletion(provider, "default"));
                    provider.addCompletion(new BasicCompletion(provider, "do"));
                    provider.addCompletion(new BasicCompletion(provider, "else"));
                    provider.addCompletion(new BasicCompletion(provider, "enum"));
                    provider.addCompletion(new BasicCompletion(provider, "extends"));
                    provider.addCompletion(new BasicCompletion(provider, "final"));
                    provider.addCompletion(new BasicCompletion(provider, "finally"));
                    provider.addCompletion(new BasicCompletion(provider, "for"));
                    provider.addCompletion(new BasicCompletion(provider, "goto"));
                    provider.addCompletion(new BasicCompletion(provider, "if"));
                    provider.addCompletion(new BasicCompletion(provider, "implements"));
                    provider.addCompletion(new BasicCompletion(provider, "import"));
                    provider.addCompletion(new BasicCompletion(provider, "instanceof"));
                    provider.addCompletion(new BasicCompletion(provider, "interface"));
                    provider.addCompletion(new BasicCompletion(provider, "native"));
                    provider.addCompletion(new BasicCompletion(provider, "new"));
                    provider.addCompletion(new BasicCompletion(provider, "package"));
                    provider.addCompletion(new BasicCompletion(provider, "private"));
                    provider.addCompletion(new BasicCompletion(provider, "protected"));
                    provider.addCompletion(new BasicCompletion(provider, "public"));
                    provider.addCompletion(new BasicCompletion(provider, "return"));
                    provider.addCompletion(new BasicCompletion(provider, "static"));
                    provider.addCompletion(new BasicCompletion(provider, "strictfp"));
                    provider.addCompletion(new BasicCompletion(provider, "super"));
                    provider.addCompletion(new BasicCompletion(provider, "switch"));
                    provider.addCompletion(new BasicCompletion(provider, "synchronized"));
                    provider.addCompletion(new BasicCompletion(provider, "this"));
                    provider.addCompletion(new BasicCompletion(provider, "throw"));
                    provider.addCompletion(new BasicCompletion(provider, "throws"));
                    provider.addCompletion(new BasicCompletion(provider, "transient"));
                    provider.addCompletion(new BasicCompletion(provider, "try"));
                    provider.addCompletion(new BasicCompletion(provider, "void"));
                    provider.addCompletion(new BasicCompletion(provider, "volatile"));
                    provider.addCompletion(new BasicCompletion(provider, "while"));

                    // Add a couple of "shorthand" completions. These completions don't
                    // require the input text to be the same thing as the replacement text.
                    provider.addCompletion(new ShorthandCompletion(provider, "sysout",
                            "System.out.println(", "System.out.println("));
                    provider.addCompletion(new ShorthandCompletion(provider, "syserr",
                            "System.err.println(", "System.err.println("));

                    return provider;

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

        SyntaxValidator syntaxValidator;
        ValidatorMessage m = SyntaxValidator.check(saveFile);

        errorArea.setText(m.getMessage());

        if(m.isValid()) {
            ValidatorMessage m2 = TestValidator.check(curStory, curStep);
            errorArea.appendText("\n" + m2.getMessage());
        }
    }
}
