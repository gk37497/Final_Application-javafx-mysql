package sample.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import sample.Main;
import sample.java.model.Quotes;
import sample.java.service.FilteredLists;
import sample.java.service.TaskPagesService;

import java.time.LocalDate;
import java.util.Random;

public class WeekSchedulePageController {

    public Main main;
    public Quotes quotes = new Quotes();
    public FilteredLists filteredLists = new FilteredLists();


    @FXML
    private Label mondayLabel;

    @FXML
    private Label thursdayLabel;


    @FXML
    private Label tuesdayLabel;

    @FXML
    private Label saturdayLabel;

    @FXML
    private Label wednesdayLabel;

    @FXML
    private Label sundayLabel;

    @FXML
    private Label fridayLabel;


    @FXML
    private ListView<Label> mondayList;

    @FXML
    private ListView<Label> thursdayList;

    @FXML
    private ListView<Label> tuesdayList;

    @FXML
    private ListView<Label> saturdayList;

    @FXML
    private ListView<Label> wednesdayList;

    @FXML
    private ListView<Label> sundayList;

    @FXML
    private ListView<Label> fridayList;

    @FXML
    private Label weekInterval;

    @FXML
    private Label mottoText;

    public Random random = new Random();

    @FXML
    void initialize(){
        LocalDate monday , sunday;
        int i = 0;
        while(true){
            if (LocalDate.now().minusDays(i).getDayOfWeek().toString().equals("MONDAY")){
                monday = LocalDate.now().minusDays(i);
                i = 1;
                break;
            }
            else{
                i++;
            }
        }
        while(true){
            if (LocalDate.now().plusDays(i).getDayOfWeek().toString().equals("MONDAY")){
                sunday = LocalDate.now().plusDays(i-1);
                i = 0;
                break;
            }
            else{
                i++;
            }
        }

        weekInterval.setText(dateConverter(monday) + "-" + dateConverter(sunday));

        switch (LocalDate.now().getDayOfWeek().toString()){
            case "MONDAY" : mondayLabel.setTextFill(Color.WHITE);
                break;
            case "TUESDAY" : tuesdayLabel.setTextFill(Color.WHITE);
                break;
            case "WEDNESDAY" : wednesdayLabel.setTextFill(Color.WHITE);
                break;
            case "THURSDAY" : thursdayLabel.setTextFill(Color.WHITE);
                break;
            case "FRIDAY" : fridayLabel.setTextFill(Color.WHITE);
                break;
            case "SATURDAY" : saturdayLabel.setTextFill(Color.WHITE);
                break;
            case "SUNDAY" : sundayLabel.setTextFill(Color.WHITE);
                break;
        }
    }

    public void scheduleMaker (String dayOfWeek , ListView<Label> dayName){
        ObservableList <Label > dayTasks = FXCollections.observableArrayList();
        filteredLists.tasksOfThisWeek(main,dayOfWeek).forEach(task -> dayTasks.add(checkCompleted(task.getTitle(),task.isCompleted())));
        dayName.setItems(dayTasks);

    }

    public Label checkCompleted(String taskTitle,boolean completed){
        Label label = new Label();
        label.setText(taskTitle);
        if (completed) {
            label.setTextFill(Color.RED);
        } else {
            label.setTextFill(Color.BLACK);
        }
        return label;
    }

    public static String dateConverter( LocalDate date){
        String month = date.getMonth().toString();
        int dayOfMonth = date.getDayOfMonth();
        return (TaskPagesService.firstLetterUpper(month) + " " + String.valueOf(dayOfMonth));
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

        mottoText.setText(quotes.getQuotes().get(random.nextInt(quotes.getQuotes().size()-1)));
    }
}
