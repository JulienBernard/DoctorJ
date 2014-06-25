package fr.intechinfo.doctorj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains the different chapters of the game.
 */
public class Storyline implements Serializable {
    // Global Storyline then we can access to the story from anywhere.
    // TODO : LEGACY
    private final static Storyline instance = new Storyline();

    public static Storyline getInstance() {
        return instance;
    }

    private List<Chapter> chapters;
    private String name;
    private String pitch;
    private String testFile;
    private String backgroundFile;
    private int currentChapter;

    public Storyline() {
        this.chapters = new ArrayList<Chapter>();
        this.name = "";
        this.pitch = "";
        this.testFile = "";
        this.backgroundFile = "";
        this.currentChapter = 0;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPitch() { return pitch; }

    public void setPitch(String pitch) { this.pitch = pitch; }

    public String getTestFile() { return testFile; }

    public void setTestFile(String testFile) { this.testFile = testFile; }

    public String getBackgroundFile() { return backgroundFile; }

    public void setBackgroundFile(String backgroundFile) { this.backgroundFile = backgroundFile; }

    public int getCurrentChapter() { return currentChapter; }

    public void setCurrentChapter(int setChapter) { this.currentChapter = setChapter; }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void resetStoryline() {
        this.chapters = new ArrayList<Chapter>();
        this.name = "";
        this.pitch = "";
        this.testFile = "";
        this.backgroundFile = "";
        this.currentChapter = 0;
    }
}