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

            String name = (String) jsonObject.get("presentationName");
            String desc = (String) jsonObject.get("presentationDescription");
            String date = (String) jsonObject.get("presentationDate");
            String autor = (String) jsonObject.get("presentationAutor");
            str += "Name: "+name+"\n";
            str += "Description: "+desc+"\n";
            str += "Date: "+date+"\n";
            str += "Autor: "+autor+"\n";

            JSONArray slideContent = (JSONArray) jsonObject.get("presentationSlides");
            Iterator i = slideContent.iterator();

            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                String title = (String)slide.get("title");
                str += "-> "+ title+"\n";

                JSONArray paragraphContent = (JSONArray) slide.get("paragraphs");
                Iterator j = paragraphContent.iterator();
                while (j.hasNext()) {
                    JSONObject paragraph = (JSONObject) j.next();
                    String value = (String)paragraph.get("value");
                    String image = (String)paragraph.get("image");
                    str += " |-> "+ value+"\n";
                    if( image != null )
                        str += " |-> "+ image+"\n";
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
