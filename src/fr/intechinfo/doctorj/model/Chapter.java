package fr.intechinfo.doctorj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a chapter in the game. A Chapter contains a list of steps.
 */
public class Chapter implements Serializable {
    private int id;
    private String title;
    private String description;
    private List<Step> steps;
    private int currentStep;

    public Chapter() {
        this.steps = new ArrayList<Step>();
        this.title = "";
        this.description = "";
        this.id = 0;
        this.currentStep = 0;
    }

    public Chapter(String title, String description, int id) {
        this(title, description, new ArrayList<Step>(), id);
    }

    private Chapter(String title, String description, List<Step> steps, int id) {
        this.title = title;
        this.description = description;
        this.steps = steps;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
}
