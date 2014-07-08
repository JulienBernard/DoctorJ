package fr.intechinfo.doctorj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an user of the game.
 */
public class User implements Serializable {
    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
