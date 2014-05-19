package fr.intechinfo.doctorj.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the different chapters of the game.
 */
public class Storyline {

    private List<Chapter> chapters;
    private String name;
    private String pitch;

    public Storyline() {
        this.chapters = new ArrayList<Chapter>();
        this.name = "";
        this.pitch = "";
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPitch() { return pitch; }

    public void setPitch(String pitch) { this.pitch = pitch; }
}
