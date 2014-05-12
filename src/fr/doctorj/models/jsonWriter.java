package fr.doctorj.models;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class jsonWriter {

    private String _pathFile;

    public jsonWriter( String pathFile )
    {
        if( pathFile.isEmpty() )
            pathFile = "./stories/test.json";
        this._pathFile = pathFile;
    }

    /**
     * Create or save a Json file with the data from the user.
     * @return
     */
    public boolean saveFile() {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("storyName", "bla bla name");
        jsonObject.put("storyPitch", "bla bla pitch");

            JSONObject chapterData = new JSONObject();
            chapterData.put("chapterName", "bla bla name");
            chapterData.put("chapterPitch", "bla bla name");
            chapterData.put("chapterSteps", "bla bla name");

                JSONObject stepData = new JSONObject();
                stepData.put("stepTitle", "bla bla name");
                stepData.put("stepHint", "bla bla name");
                stepData.put("stepFunction", "bla bla name");

                JSONArray chapterSteps = new JSONArray();
                chapterSteps.add(stepData);

            chapterData.put("chapterSteps", chapterSteps);

        JSONArray storyChapters = new JSONArray();
        storyChapters.add(chapterData);

        jsonObject.put("storyChapters", storyChapters);

        try {
            FileWriter file = new FileWriter( this._pathFile);
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
