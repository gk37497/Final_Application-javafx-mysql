package sample.java.service;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Alerts {

    public void validAlert(Stage dialogStage , String errMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errMessage);

        alert.showAndWait();
    }

}
