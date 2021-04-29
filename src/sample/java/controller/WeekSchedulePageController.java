package sample.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.Main;
import sample.java.service.Constants;

public class WeekSchedulePageController {

    public Main main;
    @FXML
    private ListView<String> mondayList;

    @FXML
    private ListView<String> thursdayList;

    @FXML
    private ListView<String> tuesdayList;

    @FXML
    private ListView<String> saturdayList;

    @FXML
    private ListView<String> wednesdayList;

    @FXML
    private ListView<String> sundayList;

    @FXML
    private ListView<String> fridayList;

    @FXML
    private Label mottoText;

    public void scheduleMaker (String dayOfWeek , ListView<String> dayName){
        ObservableList <String > dayTasks = FXCollections.observableArrayList();
        main.getTasksData().filtered(e->e.getDate().getDayOfWeek().toString().equals(dayOfWeek)).forEach(e->dayTasks.add(e.getTitle()));
        dayName.setItems(dayTasks);
    }
    //set main
    public void setMain(Main main){
        this.main = main;

        scheduleMaker("MONDAY" , mondayList);
        scheduleMaker("TUESDAY" , tuesdayList);
        scheduleMaker("WEDNESDAY" , wednesdayList);
        scheduleMaker("THURSDAY" , thursdayList);
        scheduleMaker("FRIDAY" , fridayList);
        scheduleMaker("SATURDAY" , saturdayList);
        scheduleMaker("SUNDAY" , sundayList);

        mottoText.setText("\"The greatest glory in living lies not in never falling, but in rising every time we fall.\" -Nelson Mandela");
    }
}
