package fr.intechinfo.doctorj.model;

import javafx.event.EventHandler;
import javafx.scene.Node;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.ShorthandCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Alexandre on 25/06/2014.
 */
public class RSyntaxTextAreaUtils {
    public static void fixKeyboardIssues(RSyntaxTextArea textArea, Node container) {
        // Bugs in the keymap : unlock some keys
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(e.isAltDown() && c != '}') {
                    StringBuilder b = new StringBuilder();
                    b.append(c);
                    textArea.insert(b.toString(), textArea.getCaretPosition());
                }
            }
        });

        // Unlock the other keys
        container.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent e) {
                if(e.isAltDown()) {
                    String toAdd = new String();

                    switch(e.getCode().toString()) {
                        case "DIGIT2":
                            toAdd = "~";
                            break;

                        case "DIGIT7":
                            toAdd = "`";
                            break;
                    }

                    if(!toAdd.isEmpty()) {
                        textArea.insert(toAdd, textArea.getCaretPosition());
                        textArea.setCaretPosition(textArea.getCaretPosition()+1);
                    }
                }
            }
        });
    }

    public static CompletionProvider createCompletionProvider() {

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
}
