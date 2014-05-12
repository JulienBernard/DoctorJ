package fr.intechinfo.doctorj.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chapter in the game. A Chapter contains a list of steps.
 */
public class Chapter {
    private String title;
    private String description;
    private List<Step> steps;

    public Chapter() {
        this("", "", new ArrayList<Step>());
    }

    public Chapter(String title, String description) {
        this(title, description, new ArrayList<Step>());
    }

    private Chapter(String title, String description, List<Step> steps) {
        this.title = title;
        this.description = description;
        this.steps = steps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
