package fr.intechinfo.doctorj.controllers;

import fr.intechinfo.doctorj.DoctorJ;
import fr.intechinfo.doctorj.model.Chapter;
import fr.intechinfo.doctorj.model.Step;
import fr.intechinfo.doctorj.model.Storyline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
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
    @FXML private ComboBox stepsBox;

    private Storyline str;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        str = Storyline.getInstance();

        int sizeChap = str.getChapters().size();
        int idChap = str.getCurrentChapter();
        int idStep = str.getChapters().get(idChap).getCurrentStep();

        if( sizeChap != 0 )
        {
            int sizeStep = str.getChapters().get(idChap).getSteps().size();
            if( sizeStep != 0 )
            {
                Step step = str.getChapters().get(idChap).getSteps().get(idStep);
                this.stepTitleField.setText(step.getTitle());
                this.stepHelpField.setText(step.getHelp());
                this.stepDirectionField.setText(step.getDirection());
                this.stepHintField.setText(step.getHint());
                this.stepImageField.setText(step.getImage());
                this.stepTestField.setText(step.getFunction());
            }
            else
            {
                this.stepTitleField.setText("");
                this.stepHelpField.setText("");
                this.stepDirectionField.setText("");
                this.stepHintField.setText("");
                this.stepImageField.setText("");
                this.stepTestField.setText("");
            }
        }
    }

    public void newFile(ActionEvent actionEvent) {
        str.resetStoryline();
        DoctorJ.getInstance().changeScene("generatorGame", "Doctor J - Nouvelle histoire", 800, 600);
    }

    public void loadFile(ActionEvent actionEvent) {
        int sizeChap = str.getChapters().size();
        int idChap = str.getCurrentChapter();
        int idStep = str.getChapters().get(idChap).getCurrentStep();
        if( sizeChap != 0 )
        {
            int sizeStep = str.getChapters().get(idChap).getSteps().size();
            if( sizeStep != 0 )
            {
                Step step = str.getChapters().get(idChap).getSteps().get(idStep);
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
        int idChap = str.getCurrentChapter();
        int idStep = str.getChapters().get(idChap).getCurrentStep();

        str.getChapters().get(idChap).getSteps().get(idStep).setTitle(this.stepTitleField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setHelp(this.stepHelpField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setDirection(this.stepDirectionField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setHint(this.stepHintField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setImage(this.stepImageField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setFunction(this.stepTestField.getText());
    }

    public void saveFile() {
        int idChap = str.getCurrentChapter();
        int idStep = str.getChapters().get(idChap).getCurrentStep();

        str.getChapters().get(idChap).getSteps().get(idStep).setTitle(this.stepTitleField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setHelp(this.stepHelpField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setDirection(this.stepDirectionField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setHint(this.stepHintField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setImage(this.stepImageField.getText());
        str.getChapters().get(idChap).getSteps().get(idStep).setFunction(this.stepTestField.getText());
    }

    public void quit(ActionEvent actionEvent) {
    }

    public void previous(ActionEvent actionEvent) {
        saveFile();

        int chapterSize = str.getChapters().size();
        int stepSize = str.getChapters().get(str.getCurrentChapter()).getSteps().size();

        if( str.getChapters().get(str.getCurrentChapter()).getCurrentStep() == 0 )
        {
            DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1))", 800, 600);
        } else {
            str.getChapters().get(str.getCurrentChapter()).setCurrentStep(str.getChapters().get(str.getCurrentChapter()).getCurrentStep() - 1);
            DoctorJ.getInstance().changeScene("generatorStep", "Doctor J - Ajouter une étape ("+ (str.getChapters().get(str.getCurrentChapter()).getCurrentStep() + 1) +")", 800, 600);
        }
    }

    public void next(ActionEvent actionEvent) {
        saveFile();

        int chapterSize = str.getChapters().size();
        int stepSize = str.getChapters().get(str.getCurrentChapter()).getSteps().size();

        if( str.getChapters().get(str.getCurrentChapter()).getCurrentStep() == (stepSize - 1) )
        {
            if( str.getCurrentChapter() < (chapterSize - 1) ) {
                str.setCurrentChapter(str.getCurrentChapter() + 1);
                DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape 1))", 800, 600);
            } else {
                DoctorJ.getInstance().changeScene("generatorEnd", "Doctor J - Valider mon histoire", 800, 600);
            }
        } else {
            str.getChapters().get(str.getCurrentChapter()).setCurrentStep(str.getChapters().get(str.getCurrentChapter()).getCurrentStep() + 1);
            DoctorJ.getInstance().changeScene("generatorStep", "Doctor J - Ajouter une étape (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape "+ (str.getChapters().get(str.getCurrentChapter()).getCurrentStep() + 1) +")", 800, 600);
        }
    }

    private void addStep() {
        saveFile();
        int chapterSize = str.getChapters().size();
        int stepSize = str.getChapters().get(str.getCurrentChapter()).getSteps().size();

        // Add one to the current step position (on the list)
        str.getChapters().get(str.getCurrentChapter()).getSteps().add(new Step());
        str.getChapters().get(str.getCurrentChapter()).setCurrentStep(stepSize);
        DoctorJ.getInstance().changeScene("generatorStep", "Doctor J - Ajouter une étape (Chapitre " + (str.getCurrentChapter() + 1) + ", Étape " + (str.getChapters().get(str.getCurrentChapter()).getCurrentStep() + 1) + ")", 800, 600);
    }

    private void deletedStep() {
        str.getChapters().get(str.getCurrentChapter()).getSteps().remove(str.getChapters().get(str.getCurrentChapter()).getCurrentStep());
        DoctorJ.getInstance().changeScene("generatorChapter", "Doctor J - Ajouter un chapitre", 800, 600);
    }

    public void stepSelected(ActionEvent actionEvent) {
        String value = (String) stepsBox.getValue();
        if( value.equals("Ajouter une étape") ) {
            addStep();
        } else if(value.equals("Supprimer cette étape")) {
            deletedStep();
        } else {
            System.out.println("l");
        }
    }
}
