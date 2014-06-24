package fr.intechinfo.doctorj.model;

import java.io.Serializable;

/**
 * Represents a step in the game.
 */
public class Step implements Serializable {
    private int id;
    private String title;
    private String help;
    private String direction;
    private String hint;
    private String video;
    private String function;

    public Step() {
        this.title = "";
        this.help = "";
        this.direction = "";
        this.hint = "";
        this.video = "";
        this.function = "";
        this.id = 0;
    }

    public Step(String title, String help, String direction, String hint, String video, String function, int id) {
        this.title = title;
        this.help = help;
        this.direction = direction;
        this.hint = hint;
        this.video = video;
        this.function = function;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
