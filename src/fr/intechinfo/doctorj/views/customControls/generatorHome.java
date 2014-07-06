package fr.intechinfo.doctorj.views.customControls;

import fr.intechinfo.doctorj.controllers.Generator;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Story;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.File;
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
    @FXML private ListView<Step> listSteps;
    @FXML private Button btnEditStep;
    @FXML private Button btnDelStep;

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
        fillStepList();

        listSteps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Step>() {
            @Override
            public void changed(ObservableValue<? extends Step> observableValue, Step step, Step step2) {
                if(observableValue != null) {
                    btnDelStep.setDisable(false);
                    btnEditStep.setDisable(false);
                }
                else {
                    btnDelStep.setDisable(true);
                    btnEditStep.setDisable(true);
                }
            }
        });
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

        fillForm();
    }

    public void fillStepList() {
        listSteps.getSelectionModel().clearSelection();
        btnEditStep.setDisable(true);
        btnDelStep.setDisable(true);

        ObservableList<Step> myObservableList = FXCollections.observableList(story.getSteps());
        listSteps.setItems(myObservableList);

        listSteps.setCellFactory(new Callback<ListView<Step>, ListCell<Step>>() {
            @Override
            public ListCell<Step> call(ListView<Step> stepListView) {
                ListCell<Step> cell = new ListCell<Step>() {
                    @Override
                    protected void updateItem(Step step, boolean b) {
                        super.updateItem(step, b);
                        if (step != null) {
                            setText(step.getTitle());
                        }
                    }
                };

                return cell;
            }
        });
    }

    public boolean saveStory() {
        return true;
    }

    @FXML protected void onClickOpenVideoStart(ActionEvent event) {
        FileChooser f = new FileChooser();
        f.setTitle("Sélectionnez la vidéo du début :");
        File video = f.showOpenDialog(getScene().getWindow());

        if (video != null)
            videoStart.setText(video.getAbsolutePath());

    }

    @FXML protected void onClickOpenVideoGood(ActionEvent event) {
        FileChooser f = new FileChooser();
        f.setTitle("Sélectionnez la vidéo en cas de victoire :");
        File video = f.showOpenDialog(getScene().getWindow());

        if (video != null)
            videoGood.setText(video.getAbsolutePath());
    }

    @FXML protected void onClickOpenVideoEnd(ActionEvent event) {
        FileChooser f = new FileChooser();
        f.setTitle("Sélectionnez la vidéo en cas de défaite :");
        File video = f.showOpenDialog(getScene().getWindow());

        if (video != null)
            videoBad.setText(video.getAbsolutePath());
    }

    @FXML protected void onClickBtnSave(ActionEvent event) {

    }

    @FXML protected void onClickBtnDelStory(ActionEvent event) {

    }

    @FXML protected void onClickBtnAddStep(ActionEvent event) {
        Step s = new Step("New step");
        story.getSteps().add(s);

        GeneratorStep gs = new GeneratorStep(s, story);

        Stage stage = new Stage();
        stage.setTitle("Doctor J - Edition d'une étape");
        Scene scene = new Scene(gs, 600, 500);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                fillStepList();
            }
        });
    }

    @FXML protected void onClickBtnEditStep(ActionEvent event) {

    }

    @FXML protected void onClickBtnDelStep(ActionEvent event) {
        story.getSteps().remove(listSteps.getSelectionModel().getSelectedItem());
        fillStepList();
    }
}
