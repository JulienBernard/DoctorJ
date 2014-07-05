package fr.intechinfo.doctorj.utils;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Alexandre on 25/06/2014.
 */
public class RSyntaxTextAreaUtils {
    public static void fixKeyboardIssues(final RSyntaxTextArea textArea, final Node container) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
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
            }
        });

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
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
        });
    }
}
