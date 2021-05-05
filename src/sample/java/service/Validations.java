package sample.java.service;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class Validations {

    public Stage dialogStage;

///Таск хуудасны баталгаажуулалт
    public  Boolean taskPage(TextField taskTitleField , DatePicker datePicker) throws NullPointerException{
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "Таскын гарчиг хоосон байна";
        if (datePicker.getValue() == null && datePicker.getValue().isAfter(LocalDate.now()))
            errorMessage = errorMessage + "Он сар өдөр оруулаагүй байна";
        if (errorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }
///Today хуудасны баталгаажуулалт
    public Boolean todayPage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "Таскын гарчиг хоосон байна";
        if (errorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

///Challenge хуудасны баталгаажуулалт
    public Boolean challengePage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "Зорилтын гарчиг хоосон байна";
        if (errorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

///New Challenge хуудасны баталгаажуулалт
    public Boolean newChallengeDialog(DatePicker startDatePicker , TextArea challengeDesc , TextField durationField) throws NullPointerException{
        String errMessage = "";

        if (startDatePicker.getValue() == null && startDatePicker.getValue().getDayOfYear() <= LocalDate.now().getDayOfYear())
            errMessage = "Эхлэх өдөр хоосон байна!\n";

        if (challengeDesc.getText().isEmpty())
            errMessage +="Тайлбар байхгүй байна!\n";

        if (durationField.getText().isEmpty())
            errMessage +="Үргэлжлэх хугацаа оруулаагүй байна\n";

        else{
            try {
                if(Integer.parseInt(durationField.getText()) <= 0){
                    errMessage += "Үргэлжлэх хугацаа 0 байж болохгүй\n";
                }
            } catch (NumberFormatException e) {
                errMessage += "Үргэлжлэх хугацаа тоо байх ёстой!\n";
            }
        }

        if (errMessage.isEmpty())return  true;
        else {
            Alerts.validAlert(this.dialogStage , errMessage);
            return false;
        }
    }
}
