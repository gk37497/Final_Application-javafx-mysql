package sample.java.service;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Validations {

    public Stage dialogStage;

//Таск хуудасны баталгаажуулалт
    public  Boolean taskPage(TextField taskTitleField , DatePicker datePicker){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Task title !!!";
        if (datePicker.getValue() == null && datePicker.getValue().isAfter(LocalDate.now()))
            errorMessage = errorMessage + " No valid Task Date !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }
    //Today хуудасны баталгаажуулалт
    public Boolean todayPage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Task title !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

    //Challenge хуудасны баталгаажуулалт
    public Boolean challengePage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Challenge title !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

    //New Challenge хуудасны баталгаажуулалт
    public Boolean newChallengeDialog(DatePicker startDatePicker , TextArea challengeDesc , TextField durationField) throws NullPointerException{
        String errMessage = "";

        if (startDatePicker.getValue() == null && startDatePicker.getValue().getDayOfYear() <= LocalDate.now().getDayOfYear())
            errMessage = "No valid Start date!\n";

        if (challengeDesc.getText().isEmpty())
            errMessage +="No valid Description !\n";

        if (durationField.getText().isEmpty())
            errMessage +="No valid Duration!\n";

        else{
            try {
                if(Integer.parseInt(durationField.getText()) <= 0){
                    errMessage += "Duration day is not equal = 0\n";
                }
            } catch (NumberFormatException e) {
                errMessage += "No valid Duration (must be an integer)!\n";
            }
        }

        if (errMessage.isEmpty())return  true;
        else {
            Alerts.validAlert(this.dialogStage , errMessage);
            return false;
        }
    }
}
