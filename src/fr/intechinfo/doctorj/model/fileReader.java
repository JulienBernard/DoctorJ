package fr.intechinfo.doctorj.model;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;

public class fileReader {

    private String _pathFile;

    public fileReader(String pathFile)
    {
        if( pathFile.isEmpty() )
            pathFile = "./stories/TestStory/story.json";
        this._pathFile = pathFile;
    }

    /***
     * Load a serializable Java file and return a Story object.
     * @return storyline object
     */
    public Storyline readStory() {
        ObjectInputStream ois = null;

        try {
            FileInputStream fichier = new FileInputStream( this._pathFile );
            ois = new ObjectInputStream(fichier);
            final Storyline story = (Storyline) ois.readObject();
            return story;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
