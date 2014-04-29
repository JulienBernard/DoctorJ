package fr.doctorj.controllers;

import fr.doctorj.models.jsonReader;
import javafx.fxml.FXML;
import org.json.simple.parser.JSONParser;

public class Generator {
    JSONParser parser = new JSONParser();

    public Generator() {
        jsonReader JsonReader = new jsonReader( "" );
        String str = JsonReader.readFile(parser);

    }

}
