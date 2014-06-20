package fr.intechinfo.doctorj.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class fileWriter {

    private String _pathFile;

    public fileWriter(String pathFile)
    {
        if( pathFile.isEmpty() )
            pathFile = "./stories/TestStory/test.json";
        this._pathFile = pathFile;
    }

    /**
     * Create or save a serializable Java file with the data from the user.
     * @return
     */
    public boolean saveFile( Storyline story ) {
        ObjectOutputStream oos = null;

        try {
            FileOutputStream fichier = new FileOutputStream(this._pathFile);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(story);
            oos.flush();
            return true;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
}
