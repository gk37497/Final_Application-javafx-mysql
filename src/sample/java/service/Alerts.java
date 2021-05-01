package sample.java.service;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Alerts {

    public static void validAlert(Stage dialogStage , String errMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errMessage);

        alert.showAndWait();
    }
    public static Alert deleteAlert(Stage dialogStage, String errMessage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Confirm to delete");
        alert.setHeaderText("Are you sure?");
        alert.setContentText(errMessage);
        return alert;
    }

}
