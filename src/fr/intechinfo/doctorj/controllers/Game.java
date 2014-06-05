package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.model.validators.SyntaxValidator;
import fr.intechinfo.doctorj.model.validators.TestValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for the Game view
 */
public class Game implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         
    }

    @FXML private TextArea codeArea;
    @FXML private TextArea errorArea;

    @FXML protected void handleExecuteButton(ActionEvent event) throws IOException {

        String data = codeArea.getText();

        FileUtils.writeStringToFile(new File("./stories/story1/Step1.java"), data);

        SyntaxValidator syntaxValidator;
        List<String> errorList = SyntaxValidator.check("./stories/story1/Step1.java");
        String errors = "";

        for (String anErrorList : errorList) {
            errors += anErrorList;
        }
        errorArea.setText(errors);

        if(errors.length() < 10 && codeArea.getText().length() > 6) {
            errors += TestValidator.check("story1", "Step1").getMessage();

            errorArea.setText(errors);
        }
    }

}
