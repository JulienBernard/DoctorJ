package fr.intechinfo.doctorj.model;

import java.io.Serializable;

/**
 * Represents a snippet, used by the user.
 */
public class Snippet implements Serializable {
    private Step stepConcerned;
    private String snippet;

    public Snippet(Step stepConcerned, String snippet) {
        this.stepConcerned = stepConcerned;
        this.snippet = snippet;
    }

    public Snippet(Step stepConcerned) {
        this(stepConcerned, "");
    }

    public Step getStepConcerned() {
        return stepConcerned;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}
