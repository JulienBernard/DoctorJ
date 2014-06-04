package fr.intechinfo.doctorj.model;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;

public class jsonReader {

    private String _pathFile;

    public jsonReader( String pathFile )
    {
        if( pathFile.isEmpty() )
            pathFile = "./stories/beginners.json";
        this._pathFile = pathFile;
    }

    public Map<String, String> readStory( JSONParser parser )
    {
        Map<String, String> story = new HashMap<String, String>();

        try {
            Object obj = parser.parse(new FileReader( this._pathFile));
            JSONObject jsonObject = (JSONObject) obj;

            String storyName = (String) jsonObject.get("storyName");
            String storyPitch = (String) jsonObject.get("storyPitch");
            story.put("storyName", storyName);
            story.put("storyPitch", storyPitch);
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("IO Exception was detected!");
        } catch (org.json.simple.parser.ParseException e) {
            System.err.println("An exception was detected!");
        }
        return story;
    }

    public List<Map<String, String>> readChapters( JSONParser parser )
    {
        List<Map<String, String>> chapters = new ArrayList<Map<String, String>>();

        try {
            Object obj = parser.parse(new FileReader( this._pathFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray slideContent = (JSONArray) jsonObject.get("storyChapters");
            Iterator i = slideContent.iterator();

            while (i.hasNext()) {
                Map<String, String> chapter = new HashMap<String, String>();
                JSONObject slide = (JSONObject) i.next();
                String chapterName = (String)slide.get("chapterName");
                String chapterPitch = (String)slide.get("chapterPitch");
                chapter.put("chapterName", chapterName);
                chapter.put("chapterPitch", chapterPitch);
                chapters.add(chapter);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("IO Exception was detected!");
        } catch (org.json.simple.parser.ParseException e) {
            System.err.println("An exception was detected!");
        }
        return chapters;
    }

    public List<List<Map<String, String>>> readSteps( JSONParser parser )
    {
        List<List<Map<String, String>>> allSteps = new ArrayList<List<Map<String, String>>>();
        List<Map<String, String>> steps = new ArrayList<Map<String, String>>();

        try {
            Object obj = parser.parse(new FileReader( this._pathFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray slideContent = (JSONArray) jsonObject.get("storyChapters");
            Iterator i = slideContent.iterator();

            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();

                JSONArray valueContent = (JSONArray) slide.get("chapterSteps");
                Iterator j = valueContent.iterator();

                while (j.hasNext()) {
                    Map<String, String> step = new HashMap<String, String>();
                    JSONObject value = (JSONObject) j.next();
                    String stepTitle = (String)value.get("stepTitle");
                    String stepHint = (String)value.get("stepHint");
                    String stepHelp = (String)value.get("stepHelp");
                    String stepDirection = (String)value.get("stepDirection");
                    String stepImage = (String)value.get("stepImage");
                    String stepFunction = (String)value.get("stepFunction");
                    step.put("stepTitle", stepTitle);
                    step.put("stepHint", stepHint);
                    step.put("stepHelp", stepHelp);
                    step.put("stepDirection", stepDirection);
                    step.put("stepImage", stepImage);
                    step.put("stepFunction", stepFunction);
                    steps.add(step);
                }
                allSteps.add(steps);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            JOptionPane.showMessageDialog(new Frame(), "Impossible de trouver le fichier correspondant.");
        } catch (IOException e) {
            System.err.println("IO Exception was detected!");
            JOptionPane.showMessageDialog(new Frame(), "Une erreur est survenu à l'ouverture du fichier, veuillez réessayer. Si le problème persiste, merci de prendre contact avec l'équipe de développement.");
        } catch (org.json.simple.parser.ParseException e) {
            System.err.println("An exception was detected!");
            JOptionPane.showMessageDialog(new Frame(), "L'extension du fichier doit être de forme \".json\".");
        }
        return allSteps;
    }
}
