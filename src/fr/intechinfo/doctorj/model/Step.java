package fr.intechinfo.doctorj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a step in the game.
 */
public class Step implements Serializable {
    private int id;
    private String title;
    private String help;
    private String direction;
    private String hint;
    private String image;
    private String image2;
    private String image3;
    private String imageX;
    private String imageY;
    private String image2X;
    private String image2Y;
    private String image3X;
    private String image3Y;
    private String function;

    public Step() {
        this.title = "";
        this.help = "";
        this.direction = "";
        this.hint = "";
        this.image = "";
        this.image2 = "";
        this.image3 = "";
        this.imageX = "";
        this.imageY = "";
        this.image2X = "";
        this.image2Y = "";
        this.image3X = "";
        this.image3Y = "";
        this.function = "";
        this.id = 0;
    }

    public Step(int id, String title, String help, String direction, String hint, String image, String image2, String image3, String imageX, String imageY, String image2X, String image2Y, String image3X, String image3Y) {
        this.id = id;
        this.title = title;
        this.help = help;
        this.direction = direction;
        this.function = function;
        this.hint = hint;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.imageX = imageX;
        this.imageY = imageY;
        this.image2X = image2X;
        this.image2Y = image2Y;
        this.image3X = image3X;
        this.image3Y = image3Y;
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

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image) {
        this.image2 = image;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image) {
        this.image3 = image;
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

    public String getImageX() {
        return imageX;
    }

    public void setImageX(String imageX) {
        this.imageX = imageX;
    }

    public String getImageY() {
        return imageY;
    }

    public void setImageY(String imageY) {
        this.imageY = imageY;
    }

    public String getImage2X() {
        return image2X;
    }

    public void setImage2X(String image2X) {
        this.image2X = image2X;
    }

    public String getImage2Y() {
        return image2Y;
    }

    public void setImage2Y(String image2Y) {
        this.image2Y = image2Y;
    }

    public String getImage3X() {
        return image3X;
    }

    public void setImage3X(String image3X) {
        this.image3X = image3X;
    }

    public String getImage3Y() {
        return image3Y;
    }

    public void setImage3Y(String image3Y) {
        this.image3Y = image3Y;
    }
}
