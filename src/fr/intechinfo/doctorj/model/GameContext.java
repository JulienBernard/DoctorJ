package fr.intechinfo.doctorj.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 25/06/2014.
 */
public class GameContext extends Context { // Useful to see where is the player in the game
    private List<Snippet> snippets;
    private List<StepResult> stepResults;

    private User currentUser;
    private Story currentStory;
    private Step currentStep;

    public GameContext() {
        snippets = new ArrayList<>();
        stepResults = new ArrayList<>();
    }

    public List<StepResult> getStepResults() {
        return stepResults;
    }

    public List<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Story getCurrentStory() {
        return currentStory;
    }

    public void setCurrentStory(Story currentStory) {
        this.currentStory = currentStory;
    }

    public Step getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Step currentStep) {
        this.currentStep = currentStep;
    }
}
