package fr.intechinfo.doctorj.model;

import java.io.Serializable;

/**
 * Represents a step in the game.
 */
public class Step implements Serializable {
    private String title;
    private String userFileName;
    private String help;
    private String direction;

    private String videoStart;
    private String videoLoop;

    public Step() {
        this("");
    }

    public Step(String t) {
        title = t;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserFileName() {
        return userFileName;
    }

    public void setUserFileName(String userFileName) {
        this.userFileName = userFileName;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getVideoStart() {
        return videoStart;
    }

    public void setVideoStart(String videoStart) {
        this.videoStart = videoStart;
    }

    public String getVideoLoop() {
        return videoLoop;
    }

    public void setVideoLoop(String videoLoop) {
        this.videoLoop = videoLoop;
    }
}
