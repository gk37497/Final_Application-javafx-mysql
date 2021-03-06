package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.java.dao.TaskDao;
import sample.java.model.Challenge;
import sample.java.model.Task;
import sample.java.service.Validations;

public class NewChallengeDialogController {

    public Stage dialogStage;
    public boolean _bOkClicked = false;
    public Main main;

    public Validations validations = new Validations();

    @FXML
    private Label challengeTitleLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextArea challengeDesc;

    @FXML
    private TextField durationField;

    public Challenge challenge;

//Set Challenge
public void setChallenge(Challenge newChallenge, String title){
    this.challenge = newChallenge;

    challengeTitleLabel.setText(title);
    challenge.setTitle(title);
}

public boolean is_bOkClicked(){
    return _bOkClicked;
}

// Handle Cancel button
    @FXML
    void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

//Handle Start button
    @FXML
    void handleStart(ActionEvent event) {

    if(validations.newChallengeDialog(startDatePicker,challengeDesc,durationField)){

        challenge.setDescription(challengeDesc.getText());
        challenge.setStartedDate(startDatePicker.getValue());
        challenge.setDuration(Integer.parseInt(durationField.getText()));
        challenge.setType("active");
        challenge.setCompleted(false);

        for (int i = 0; i < challenge.getDuration(); i++) {
            Task task = new Task(challenge.getTitle() + " "+ "Өдөр" + (i+1),"challenge",false, challenge.getStartedDate().plusDays(i));
            TaskDao.writeTask(task);
            main.getTasksData().add(task);
        }

        _bOkClicked = true;
        dialogStage.close();
    }
}
    //Set Main
    public void setMain(Main main) { this.main = main; }

    //Set Stage
    public void setStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

}
