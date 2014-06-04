package fr.intechinfo.doctorj.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a step in the game.
 */
public class Step {
    private int id;
    private String title;
    private String help;
    private String direction;
    private String hint;
    private String image;
    private String function;

    public Step() {
        this.title = "";
        this.help = "";
        this.direction = "";
        this.hint = "";
        this.image = "";
        this.function = "";
        this.id = 0;
    }

    public Step(String title, String help, String direction, String hint, String image, String function, int id) {
        this.title = title;
        this.help = help;
        this.direction = direction;
        this.hint = hint;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
