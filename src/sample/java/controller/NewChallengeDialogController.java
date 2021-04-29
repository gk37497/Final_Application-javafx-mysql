package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.java.model.Challenge;
import sample.java.service.ChallengePageService;
import sample.java.service.Validations;

public class NewChallengeDialogController {

    public Stage dialogStage;
    public Boolean okClicked = false;

    public Validations validations = new Validations();

    @FXML
    private Label challengeTitleLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextArea challengeDesc;

    @FXML
    private TextField durationField;

    @FXML
    private Button startBtn;

    @FXML
    private Button cancelBtn;

    public Challenge challenge;

//Set Challenge
public void setChallenge(Challenge newChallenge, String title){
    this.challenge = newChallenge;
    challengeTitleLabel.setText(title);
    challenge.setTitle(title);
}

public boolean isOkClicked(){
    return okClicked;
}

// Handle Cancel button
    @FXML
    void handleCancel(ActionEvent event) {
        dialogStage.close();
    }

//Handle Start button
    @FXML
    void handleStart(ActionEvent event) {

    if(validations.isInputValidNewChallengeDialog(startDatePicker,challengeDesc,durationField)){

        challenge.setDescription(challengeDesc.getText());
        challenge.setStartedDate(startDatePicker.getValue());
        challenge.setDuration(Integer.parseInt(durationField.getText()));
        challenge.setType("active");

        okClicked = true;
        dialogStage.close();
    }
}

//Set Stage
    public void setStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

}
