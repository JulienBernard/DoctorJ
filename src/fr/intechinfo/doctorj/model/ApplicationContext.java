package fr.intechinfo.doctorj.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandre on 25/06/2014.
 */
public class ApplicationContext extends Context {
    private static ApplicationContext instance; // Stories discovered by the application context
    private String appPath;
    private String storiesPath;
    private String applicationContextFile;

    private GameContext currentGameContext;
    private List<Story> stories;

    private ApplicationContext() {

        try {
            appPath = new File(".").getCanonicalPath();
            storiesPath = appPath + "/stories";
            applicationContextFile = appPath + "/appcontext.drj";

        } catch (IOException e) {
            e.printStackTrace();
        }

        currentGameContext = new GameContext();
        stories = new ArrayList<>();
    }

    public GameContext getCurrentGameContext() {
        return currentGameContext;
    }

    public void setCurrentGameContext(GameContext g) {
        currentGameContext = g;
    }

    public static ApplicationContext getInstance() {
        if(instance == null) {
            instance = new ApplicationContext();
        }

        return instance;
    }

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getStoriesPath() {
        return storiesPath;
    }

    public void setStoriesPath(String storiesPath) {
        this.storiesPath = storiesPath;
    }

    public String getApplicationContextFile() {
        return applicationContextFile;
    }

    public void setApplicationContextFile(String applicationContextFile) {
        this.applicationContextFile = applicationContextFile;
    }
}
