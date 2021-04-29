package sample.java.service;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Validations {

    public Alerts alerts = new Alerts();
    public Stage dialogStage;

//Task fields validation
    public Boolean isInputValidTasksPage(TextField taskTitleField , DatePicker datePicker){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Task title !!!";
        if (datePicker.getValue() == null)
            errorMessage = errorMessage + " No valid Task Date !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }
//Today pages validation
    public Boolean isInputValidTodayPage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Task title !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

//Challenge page fields validation
    public Boolean isInputValidChallengesPage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Challenge title !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

//New challenge dialog validation
    public Boolean isInputValidNewChallengeDialog(DatePicker startDatePicker , TextArea challengeDesc , TextField durationField){
        String errMessage = "";

        if (startDatePicker.getValue() == null){
            errMessage = "No valid Start date!\n";
        }
        if (challengeDesc.getText().isEmpty()){
            errMessage +="No valid Description !\n";
        }
        if (durationField.getText().isEmpty()){
            errMessage +="No valid Duration!\n";
        }
        else{
            try {
                if(Integer.parseInt(durationField.getText()) <= 0){
                    errMessage += "Duration day is not equal = 0\n";
                }
            } catch (NumberFormatException e) {
                errMessage += "No valid Duration (must be an integer)!\n";
            }
        }
        if (errMessage.isEmpty()){return  true;}
        else {
            alerts.validAlert(this.dialogStage , errMessage);
            return false;
        }
    }
}
