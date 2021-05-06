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
        String _strErrorMessage = "";

        if (taskTitleField.getText().isEmpty())
            _strErrorMessage = "Таскын гарчиг хоосон байна";
        if (datePicker.getValue() == null && datePicker.getValue().isAfter(LocalDate.now()))
            _strErrorMessage = _strErrorMessage + "Он сар өдөр оруулаагүй байна";
        if (_strErrorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,_strErrorMessage);
            return false;
        }
    }
///Today хуудасны баталгаажуулалт
    public Boolean todayPage(TextField taskTitleField){
        String _strErrorMessage = "";

        if (taskTitleField.getText().isEmpty())
            _strErrorMessage = "Таскын гарчиг хоосон байна";
        if (_strErrorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,_strErrorMessage);
            return false;
        }
    }

///Challenge хуудасны баталгаажуулалт
    public Boolean challengePage(TextField taskTitleField){
        String _strErrorMessage = "";

        if (taskTitleField.getText().isEmpty())
            _strErrorMessage = "Зорилтын гарчиг хоосон байна";
        if (_strErrorMessage.length() == 0)
            return true;
        else{
            Alerts.validAlert(this.dialogStage ,_strErrorMessage);
            return false;
        }
    }

///New Challenge хуудасны баталгаажуулалт
    public Boolean newChallengeDialog(DatePicker startDatePicker , TextArea challengeDesc , TextField durationField) throws NullPointerException{
        String _strErrorMessage = "";

        if (startDatePicker.getValue() == null && startDatePicker.getValue().getDayOfYear() <= LocalDate.now().getDayOfYear())
            _strErrorMessage = "Эхлэх өдөр хоосон байна!\n";

        if (challengeDesc.getText().isEmpty())
            _strErrorMessage +="Тайлбар байхгүй байна!\n";

        if (durationField.getText().isEmpty())
            _strErrorMessage +="Үргэлжлэх хугацаа оруулаагүй байна\n";

        else{
            try {
                if(Integer.parseInt(durationField.getText()) <= 0){
                    _strErrorMessage += "Үргэлжлэх хугацаа 0 байж болохгүй\n";
                }
            } catch (NumberFormatException e) {
                _strErrorMessage += "Үргэлжлэх хугацаа тоо байх ёстой!\n";
            }
        }

        if (_strErrorMessage.isEmpty())return  true;
        else {
            Alerts.validAlert(this.dialogStage , _strErrorMessage);
            return false;
        }
    }
}
