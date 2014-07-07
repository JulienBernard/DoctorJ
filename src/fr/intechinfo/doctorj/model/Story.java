package fr.intechinfo.doctorj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a "chapter" in the game. A Story contains a list of steps.
 */
public class Story implements Serializable {
    private String title;
    private String shortName;
    private String description;
    private List<Step> steps;
    private String videoStart;
    private String videoBadEnd;
    private String videoGoodEnd;
    private int currentStep;

    public Story(String title, String description) {
        this.title = title;
        this.description = description;
        steps = new ArrayList<>();
    }

    public Story() {
        this("", "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getVideoStart() {
        return videoStart;
    }

    public void setVideoStart(String videoStart) {
        this.videoStart = videoStart;
    }

    public String getVideoBadEnd() {
        return videoBadEnd;
    }

    public void setVideoBadEnd(String videoBadEnd) {
        this.videoBadEnd = videoBadEnd;
    }

    public String getVideoGoodEnd() {
        return videoGoodEnd;
    }

    public void setVideoGoodEnd(String videoGoodEnd) {
        this.videoGoodEnd = videoGoodEnd;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
}
