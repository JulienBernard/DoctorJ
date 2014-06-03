package fr.intechinfo.doctorj.model;

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
    public boolean saveFile( Storyline story ) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("storyName", story.getName());
        jsonObject.put("storyPitch", story.getPitch());
        jsonObject.put("storyTestFile", story.getTestFile());

        JSONArray storyChapters = new JSONArray();
        for( int i = 0 ; i < story.getChapters().size() ; i++ )
        {
            JSONObject chapterData = new JSONObject();
            chapterData.put("chapterName", story.getChapters().get(i).getTitle());
            chapterData.put("chapterPitch", story.getChapters().get(i).getDescription());

            JSONArray chapterSteps = new JSONArray();
            for( int j = 0 ; j < story.getChapters().get(i).getSteps().size() ; j++ )
            {
                JSONObject stepData = new JSONObject();
                stepData.put("stepTitle", story.getChapters().get(i).getSteps().get(j).getTitle());
                stepData.put("stepHelp", story.getChapters().get(i).getSteps().get(j).getHelp());
                stepData.put("stepHint", story.getChapters().get(i).getSteps().get(j).getHint());
                stepData.put("stepDirection", story.getChapters().get(i).getSteps().get(j).getDirection());
                stepData.put("stepImage", story.getChapters().get(i).getSteps().get(j).getImage());
                stepData.put("stepFunction", story.getChapters().get(i).getSteps().get(j).getFunction());
                chapterSteps.add(stepData);
            }
            chapterData.put("chapterSteps", chapterSteps);
            storyChapters.add(chapterData);
        }
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
