package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Storyline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Home view
 */
public class GeneratorStep extends Generator implements Initializable {
    @FXML private TextField stepTitleField;
    @FXML private TextArea stepHelpField;
    @FXML private TextArea stepDirectionField;
    @FXML private TextArea stepHintField;
    @FXML private TextField stepImageField;
    @FXML private TextField stepTestField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int sizeChap = DoctorJ.getInstance().getStory().getChapters().size();
        int idChap = DoctorJ.getInstance().getStory().getCurrentChapter();
        int idStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getCurrentStep();
        if( sizeChap != 0 )
        {
            int sizeStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().size();
            if( sizeStep != 0 )
            {
                Step step = DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep);
                this.stepTitleField.setText(step.getTitle());
                this.stepHelpField.setText(step.getHelp());
                this.stepDirectionField.setText(step.getDirection());
                this.stepHintField.setText(step.getHint());
                this.stepImageField.setText(step.getImage());
                this.stepTestField.setText(step.getFunction());
            }
        }
    }

    public void newFile(ActionEvent actionEvent) {
        DoctorJ.getInstance().setStory(new Storyline());
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        int sizeChap = DoctorJ.getInstance().getStory().getChapters().size();
        int idChap = DoctorJ.getInstance().getStory().getCurrentChapter();
        int idStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getCurrentStep();
        if( sizeChap != 0 )
        {
            int sizeStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().size();
            if( sizeStep != 0 )
            {
                Step step = DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep);
                this.stepTitleField.setText(step.getTitle());
                this.stepHelpField.setText(step.getHelp());
                this.stepDirectionField.setText(step.getDirection());
                this.stepHintField.setText(step.getHint());
                this.stepImageField.setText(step.getImage());
                this.stepTestField.setText(step.getFunction());
            }
        }
    }

    public void closeFile(ActionEvent actionEvent) {
    }


    public void saveFile(ActionEvent actionEvent) {
        int sizeChap = DoctorJ.getInstance().getStory().getChapters().size();
        int idChap = DoctorJ.getInstance().getStory().getCurrentChapter();
        int idStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getCurrentStep();
        int sizeStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().size();

        if( sizeChap != 0 ) {
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().add(new Step(this.stepTitleField.getText(), this.stepHelpField.getText(), this.stepDirectionField.getText(), this.stepHintField.getText(), this.stepImageField.getText(), this.stepTestField.getText(), sizeStep));
        } else if( DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(sizeStep - 1).getId() == idStep ) {
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setTitle(this.stepTitleField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setHelp(this.stepHelpField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setDirection(this.stepDirectionField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setHint(this.stepHintField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setImage(this.stepImageField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setFunction(this.stepTestField.getText());
        } else {
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().add(new Step(this.stepTitleField.getText(), this.stepHelpField.getText(), this.stepDirectionField.getText(), this.stepHintField.getText(), this.stepImageField.getText(), this.stepTestField.getText(), sizeStep));
        }
    }

    public void saveFile() {
        int sizeChap = DoctorJ.getInstance().getStory().getChapters().size();
        int idChap = DoctorJ.getInstance().getStory().getCurrentChapter();
        int idStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getCurrentStep();
        int sizeStep = DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().size();

        if( sizeChap != 0 ) {
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().add(new Step(this.stepTitleField.getText(), this.stepHelpField.getText(), this.stepDirectionField.getText(), this.stepHintField.getText(), this.stepImageField.getText(), this.stepTestField.getText(), sizeStep));
        } else if( DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(sizeStep - 1).getId() == idStep ) {
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setTitle(this.stepTitleField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setHelp(this.stepHelpField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setDirection(this.stepDirectionField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setHint(this.stepHintField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setImage(this.stepImageField.getText());
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().get(idStep).setFunction(this.stepTestField.getText());
        } else {
            DoctorJ.getInstance().getStory().getChapters().get(idChap).getSteps().add(new Step(this.stepTitleField.getText(), this.stepHelpField.getText(), this.stepDirectionField.getText(), this.stepHintField.getText(), this.stepImageField.getText(), this.stepTestField.getText(), sizeStep));
        }
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void previous(ActionEvent actionEvent) {
        saveFile();
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre", 800, 600);
    }
}
