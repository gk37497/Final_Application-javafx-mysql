package sample.java.service;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Alerts {

    public static void validAlert(Stage dialogStage , String errMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Алдаатай");
        alert.setHeaderText("Бүгдийг алдаагүй бөглөнө үү");
        alert.setContentText(errMessage);

        alert.showAndWait();
    }
    public static Alert deleteAlert(Stage dialogStage, String errMessage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Устгахыг зөвшөөрөх");
        alert.setHeaderText("Та итгэлтэй байна уу?");
        alert.setContentText(errMessage);
        return alert;
    }

}
