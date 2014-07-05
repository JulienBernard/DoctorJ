package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.model.Story;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * Created by Alexandre on 05/07/2014.
 */
public class GeneratorHome extends VBox {
    private Story story;

    @FXML private TextField storyName;
    @FXML private TextField storyShortName;
    @FXML private TextArea storyDesc;
    @FXML private TextField videoStart;
    @FXML private TextField videoGood;
    @FXML private TextField videoBad;
    @FXML private Label lblStoryName;

    public GeneratorHome(Story s) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generatorHome.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        AnchorPane.setTopAnchor(this, 0.0);
        AnchorPane.setBottomAnchor(this,0.0);
        AnchorPane.setLeftAnchor(this,0.0);
        AnchorPane.setRightAnchor(this,0.0);

        story = s;

        fillForm();
    }

    public void fillForm() {
        lblStoryName.setText(story.getTitle());
        storyName.setText(story.getTitle());
        storyShortName.setText(story.getShortName());
        storyDesc.setText(story.getDescription());
        videoStart.setText(story.getVideoStart());
        videoGood.setText(story.getVideoGoodEnd());
        videoBad.setText(story.getVideoBadEnd());
    }

    public void changeStory() {
        story.setTitle(lblStoryName.getText());
        story.setTitle(storyName.getText());
        story.setShortName(storyShortName.getText());
        story.setDescription(storyDesc.getText());
        story.setVideoStart(videoStart.getText());
        story.setVideoGoodEnd(videoGood.getText());
        story.setVideoBadEnd(videoBad.getText());
    }

    public boolean saveStory() {
        return true;
    }
}
