package sample.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Main;
import sample.java.model.Quotes;
import sample.java.model.Task;
import sample.java.service.FilteredLists;

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

        weekInterval.setText(dateConverter(monday) + " - " + dateConverter(sunday));

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
        filteredLists.tasksOfDay(main,dayOfWeek).forEach(task -> dayTasks.add(checkTask(task)));
        dayName.setItems(dayTasks);
    }

    public Label checkTask(Task task){
        Label taskTittle = new Label();

        taskTittle.setText(task.getTitle());
        taskTittle.setFont(new Font(9.0));

        if (task.isCompleted()) {
            taskTittle.setTextFill(Color.web("#855a99"));
        }else if(!task.isCompleted() && task.getDate().isBefore(LocalDate.now())){
            taskTittle.setTextFill(Color.web("#ff751f"));
        }
        else {
            taskTittle.setTextFill(Color.web("#f2f2f2"));
        }
        return taskTittle;
    }

    public static String dateConverter( LocalDate date){
        int _iMonth = date.getMonthValue();
        int _iDayOfMonth = date.getDayOfMonth();
        return (String.valueOf(_iMonth) + " сарын " + String.valueOf(_iDayOfMonth));
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
