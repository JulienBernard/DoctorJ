package fr.intechinfo.doctorj;

import fr.intechinfo.doctorj.controllers.Home;
import fr.intechinfo.doctorj.model.GameContext;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Story;
import fr.intechinfo.doctorj.utils.Paths;
import fr.intechinfo.doctorj.utils.Serialization;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class DoctorJ extends Application {
    private static GameContext currentGameContext;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage mainWindow) throws IOException {
        Platform.setImplicitExit(true);

        mainWindow.getIcons().add(new Image(DoctorJ.class.getResourceAsStream("assets/minilogoico.png")));

        mainWindow.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Serialization.saveFile(getCurrentGameContext(), Paths.getAppPath() + "/default.ctx");

                Platform.exit();
                System.exit(0);
            }
        });

        File gameContext = new File(Paths.getAppPath() + "/default.ctx");

        if(gameContext.exists()) {
            setCurrentGameContext(Serialization.loadFile(gameContext.getAbsolutePath()));
        } else {
            setCurrentGameContext(new GameContext());
        }

        Home home = new Home(mainWindow, "home");
        home.show("Accueil", 900, 600);

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