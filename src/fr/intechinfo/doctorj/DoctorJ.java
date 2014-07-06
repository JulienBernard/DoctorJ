package fr.intechinfo.doctorj;

import fr.intechinfo.doctorj.controllers.Home;
import fr.intechinfo.doctorj.model.GameContext;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.utils.Serialization;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class DoctorJ extends Application {
    private static GameContext currentGameContext;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainWindow) throws IOException {
        setCurrentGameContext(new GameContext());

        Home home = new Home(mainWindow, "home");
        home.show("Accueil", 800, 600);

        mainWindow.show();

        test();
    }

    public static GameContext getCurrentGameContext() {
        return currentGameContext;
    }

    public static void setCurrentGameContext(GameContext gc) {
        currentGameContext = gc;
    }

    public void test() {
        Story s = new Story();
        s.setShortName("story1");
        s.setTitle("tamerelastory");

        Step st = new Step();
        st.setUserFileName("Step1");
        st.setTitle("tamere");
        s.getSteps().add(st);

        getCurrentGameContext().setCurrentStory(s);
        getCurrentGameContext().setCurrentStep(st);
    }
}