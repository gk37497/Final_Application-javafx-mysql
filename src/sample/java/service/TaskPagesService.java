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
import sample.Main;
import sample.java.dao.TaskDao;
import sample.java.model.Task;

import java.time.LocalDate;
import java.util.Optional;

public class TaskPagesService {

    public ListView<BorderPane> listView;
    public ListView<BorderPane> completedListView;
    public Stage dialogStage;
    public Main main;

    public  BorderPane maker(Task newTask , ListView<BorderPane> listView , ListView<BorderPane> completedListView , Main main){
        this.completedListView = completedListView;

        /* Task-ийн мэдээллүүд.*/
        String month = firstLetterUpper(newTask.getDate().getMonth().toString());
        String year = newTask.getDate().getYear() > LocalDate.now().getYear() ? String.valueOf(newTask.getDate().getYear()) : " ";
        String dayOfMonth = String.valueOf(newTask.getDate().getDayOfMonth());
        String date = dayOfMonth + "," + month + " " + year;
        String day = newTask.getDate().equals(LocalDate.now()) ? "Today" : date;

        /* Биелэгдсэн  Task-ийг дэмдэглэх Radio Button.*/

        RadioButton radioButton = new RadioButton();
        radioButton.setPadding(new Insets(10,0,0,0));
        radioButton.setDisable(newTask.isCompleted());

        /* Task - ийн мэдээлэл дэлгэцэнд харуулах Border Pane.*/

        BorderPane borderPane = new BorderPane();
        borderPane.setId(newTask.isCompleted() ? "completedTask" : "taskRow");
        borderPane.setPrefHeight(40);

        //Radia button дарагдах үед
        radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(radioButton.isSelected() && !newTask.isCompleted()){

                    TaskDao.updateCompletedCol(newTask);

                    borderPane.setDisable(true);
                    newTask.setCompleted(true);

                    listView.getItems().remove(borderPane);
                    completedListView.getItems().add(borderPane);
                    completedListView.setVisible(true);
                }
            }
        });

        /* Task устах үед*/

        EventHandler<ActionEvent> deleteBtnEvent = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Optional<ButtonType> result = Alerts.deleteAlert(dialogStage,"Таск устгах").showAndWait();
                if(result.orElse(ButtonType.CANCEL) == ButtonType.OK){

                    listView.getItems().remove(borderPane);
                    completedListView.getItems().remove(borderPane);
                    TaskDao.deleteTask(newTask.getTitle());
                    main.getTasksData().remove(newTask);

                }
            }
        };

        /* Task устгах товчлуур*/

        Button deleteBtn = new Button("устгах");
        deleteBtn.setId("deleteBtn");
        deleteBtn.setOnAction(deleteBtnEvent);

        Label taskTitle = new Label(newTask.getTitle());
        taskTitle.setTextFill(Color.web("#ffffff"));

        Label taskDate = new Label(day);
        taskDate.setId("taskDate");

        VBox vBox = new VBox(taskTitle,taskDate);
        vBox.setPadding(new Insets(5,0,0,20));

        borderPane.setLeft(radioButton);
        borderPane.setCenter(vBox);
        borderPane.setRight(deleteBtn);

        return borderPane;
    }

    /* шинэ task-ийг өгөгдлийн сан руу нэмж дэлгэцэнд харуулах. */

    public void addTask(Main main , Task newTask , ListView<BorderPane> tasksList ,ListView<BorderPane> completedList){

        main.getTasksData().add(newTask);
        main.getTasksList().add(maker(newTask , tasksList,completedList , main));
        TaskDao.writeTask(newTask);
    }

    /* Он сар өдрийг хувиргагч (MONDAY MAY 8 => Monday, May, 8) .*/
    public static String dateConverter( LocalDate date){
        String dayOfWeek = date.getDayOfWeek().toString();
        String month = date.getMonth().toString();
        int dayOfMonth = date.getDayOfMonth();
        return (TaskPagesService.firstLetterUpper(dayOfWeek) + "," + TaskPagesService.firstLetterUpper(month) + " " + String.valueOf(dayOfMonth));
    }
    /* Эхний үсгээс бүсал үсгийг жижиг болгох*/
    public static String firstLetterUpper(String s){
            return s.charAt(0) + s.substring(1).toLowerCase();
        }
}
