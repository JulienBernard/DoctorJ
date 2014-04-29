package fr.doctorj.models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class jsonReader {

    private String _pathFile;

    public jsonReader( String pathFile )
    {
        if( pathFile.isEmpty() )
            pathFile = "./stories/beginners.json";
        this._pathFile = pathFile;
    }

    public String readFile( JSONParser parser )
    {
        String str = "";

        try {
            Object obj = parser.parse(new FileReader( this._pathFile));

            JSONObject jsonObject = (JSONObject) obj;

            String storyName = (String) jsonObject.get("storyName");
            String storyDescription = (String) jsonObject.get("chapterDescription");
            str += "Story name: "+storyName+"\n";
            str += "Story description: "+storyDescription+"\n";

            JSONArray slideContent = (JSONArray) jsonObject.get("storyChapters");
            Iterator i = slideContent.iterator();

            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                String chapterName = (String)slide.get("chapterName");
                String chapterDescription = (String)slide.get("chapterDescription");
                str += "-> "+ chapterName+"\n";
                str += "-> "+ chapterDescription+"\n";

                JSONArray paragraphContent = (JSONArray) slide.get("chapterSteps");
                Iterator j = paragraphContent.iterator();
                while (j.hasNext()) {
                    JSONObject paragraph = (JSONObject) j.next();
                    String stepTitle = (String)paragraph.get("stepTitle");
                    String stepHint = (String)paragraph.get("stepHint");
                    String stepFunction = (String)paragraph.get("stepFunction");
                    str += " |-> "+ stepTitle+"\n";
                    str += " |-> "+ stepHint+"\n";
                    str += " |-> "+ stepFunction+"\n";
                }
            }
            str += "\n";

        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("IO Exception was detected!");
        } catch (org.json.simple.parser.ParseException e) {
            System.err.println("An exception was detected!");
            str = "Your file need to be in UTF-8 WITHOUT BOM encodage!";
        }

        return str;
    }
}
