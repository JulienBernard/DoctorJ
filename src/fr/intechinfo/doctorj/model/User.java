package fr.intechinfo.doctorj.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an user of the game.
 */
public class User {
    private String name;
    private List<Step> stepsDone;
    private List<Snippet> snippets;


    public User(){
        this("", new ArrayList<Step>(), new ArrayList<Snippet>());
    }

    public User(String name){
        this(name, new ArrayList<Step>(), new ArrayList<Snippet>());
    }

    private User(String name, List<Step> stepsDone, List<Snippet> snippets) {
        this.name = name;
        this.stepsDone = stepsDone;
        this.snippets = snippets;
    }

    public String getName() {
        return name;
    }

    public List<Step> getStepsDone() {
        return stepsDone;
    }

    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setName(String name) {
        this.name = name;
    }
}
