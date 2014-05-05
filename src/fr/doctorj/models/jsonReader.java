package fr.doctorj.models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

    /**
     * Create or save a Json file with the data from the user.
     * @return
     */
    public boolean saveFile() {
        return false;
    }

    /**
     * Put on a Map all the data included on the JSon file.
     * @param parser
     * @return
     */
    public List<Map<String, String>> readFile( JSONParser parser )
    {
        // A supr
        String str = "";

        List<Map<String, String>> map = new ArrayList<>();
        Map<String, String> story = new HashMap<String, String>();
        Map<String, String> chapters = new HashMap<String, String>();

        try {
            Object obj = parser.parse(new FileReader( this._pathFile));

            JSONObject jsonObject = (JSONObject) obj;

            String storyName = (String) jsonObject.get("storyName");
            String storyPitch = (String) jsonObject.get("storyPitch");
            story.put("storyName", storyName);
            story.put("storyPitch", storyPitch);

            JSONArray slideContent = (JSONArray) jsonObject.get("storyChapters");
            Iterator i = slideContent.iterator();

            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                String chapterName = (String)slide.get("chapterName");
                String chapterPitch = (String)slide.get("chapterPitch");
                chapters.put("chapterName", chapterName);
                chapters.put("chapterPitch", chapterPitch);

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

        map.add(story);
        map.add(chapters);
        return map;
    }
}
