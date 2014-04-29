package fr.doctorj.controllers;

import com.sun.java.swing.plaf.windows.resources.windows;
import fr.doctorj.models.jsonReader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.json.simple.parser.JSONParser;

public class GeneratorController {
    JSONParser parser = new JSONParser();
    public String strReturn;

    public GeneratorController() {
        jsonReader JsonReader = new jsonReader( "" );
        strReturn = JsonReader.readFile(parser);

        System.out.println(strReturn);
    }

}
