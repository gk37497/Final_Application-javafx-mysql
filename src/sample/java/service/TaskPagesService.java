package sample.java.service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.java.model.Task;

import java.time.LocalDate;

public class TaskPagesService {

    public Stage dialogStage;
    public ListView<BorderPane> listView;
    public ListView<BorderPane> completedListView;

    public BorderPane Maker(Task newTask , ListView<BorderPane> listView , ListView<BorderPane> completedListView){

            this.listView = listView;
            this.completedListView = completedListView;
            RadioButton radioButton = new RadioButton();
            BorderPane borderPane = new BorderPane();
            Button deleteBtn = new Button("delete");


            radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(radioButton.isSelected() && newTask.getType().equals("active")){
                        borderPane.setDisable(true);
                        radioButton.setSelected(true);
                        newTask.setType("completed");
                        listView.getItems().remove(borderPane);
                        completedListView.getItems().add(borderPane);
                        completedListView.setVisible(true);

                    }
                }
            });

            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                 public void handle(ActionEvent e)
                 {
                     listView.getItems().remove(borderPane);
                  }
                };
            deleteBtn.setOnAction(event);
            borderPane.setDisable(newTask.getType().equals("completed"));
            radioButton.setSelected(newTask.getType().equals("completed"));

            Label label = new Label(newTask.getTitle());

            String month = firstLetterUpper(newTask.getDate().getMonth().toString());
            String year = newTask.getDate().getYear() > LocalDate.now().getYear() ? String.valueOf(newTask.getDate().getYear()) : " ";
            String dayOfMonth = String.valueOf(newTask.getDate().getDayOfMonth());
            String date = dayOfMonth + "," + month + " " + year;
            String day = newTask.getDate().equals(LocalDate.now()) ? "Today" : date;

            Label label1 = new Label(day);


            label1.setStyle("-fx-font-size : 10px");
            label.setTextFill(Color.web("#000000"));
            label1.setTextFill(Color.web("#B73A52"));

            VBox vBox = new VBox(label,label1);

            vBox.setPadding(new Insets(0,0,0,20));
            borderPane.setPadding(new Insets(2,0,0,10));
            borderPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));

            borderPane.setLeft(radioButton);
            borderPane.setCenter(vBox);
            borderPane.setRight(deleteBtn);

            return borderPane;
         }


    public void validAlert(Stage dialogStage , String errMessage){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errMessage);

            alert.showAndWait();
        }


    public String firstLetterUpper(String s){
            return s.charAt(0) + s.substring(1).toLowerCase();
        }


    public Boolean isInputValidTasksPage(TextField taskTitleField , DatePicker datePicker){
            String errorMessage = "";

            if (taskTitleField.getText().isEmpty())
                errorMessage = "No valid Task title !!!";
            if (datePicker.getValue() == null)
                errorMessage = errorMessage + " No valid Task Date !!!";
             if (errorMessage.length() == 0)
                return true;
            else{
                validAlert(this.dialogStage ,errorMessage);
                return false;
                }
        }

    public Boolean isInputValidTodayPage(TextField taskTitleField){
        String errorMessage = "";

        if (taskTitleField.getText().isEmpty())
            errorMessage = "No valid Task title !!!";
        if (errorMessage.length() == 0)
            return true;
        else{
            validAlert(this.dialogStage ,errorMessage);
            return false;
        }
    }

}
