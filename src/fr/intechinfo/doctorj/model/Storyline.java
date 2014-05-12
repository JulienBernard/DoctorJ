package fr.intechinfo.doctorj.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains the different chapters of the game.
 */
public class Storyline {

    private List<Chapter> chapters;

    public Storyline() {
        chapters = new ArrayList<Chapter>();
    }

    public List<Chapter> getChapters() {
        return chapters;
    }
}
