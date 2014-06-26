package fr.intechinfo.doctorj;

import fr.intechinfo.doctorj.controllers.Home;
import fr.intechinfo.doctorj.model.ApplicationContext;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Story;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class DoctorJ extends Application {
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainWindow) throws IOException {
        applicationContext = ApplicationContext.getInstance();

        Home home = new Home(mainWindow, "home");
        home.show("Accueil", 800, 600);

        mainWindow.show();

        test();
    }

    public void test() {
        Story s = new Story();
        s.setShortName("story1");

        Step st = new Step();
        st.setShortName("Step1");

        applicationContext.getCurrentGameContext().setCurrentStory(s);
        applicationContext.getCurrentGameContext().setCurrentStep(st);
    }
}
