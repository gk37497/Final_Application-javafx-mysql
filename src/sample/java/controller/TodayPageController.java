package sample.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import sample.Main;
import sample.java.dao.taskDao;
import sample.java.model.Task;
import sample.java.service.TaskPagesService;
import sample.java.service.Validations;

import java.time.LocalDate;

public class TodayPageController {

    public Main main;
    public TaskPagesService service = new TaskPagesService();
    public Validations validations = new Validations();

    @FXML
    private ListView<BorderPane> tasksList;

    @FXML
    private Label subTitle;

    @FXML
    private TextField todayTaskField;

    @FXML
    private ListView<BorderPane> completedList;

    @FXML
    private Label completedLabel;


//initialize
    @FXML
    void initialize(){

        String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        String month = LocalDate.now().getMonth().toString();
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        subTitle.setText(service.firstLetterUpper(dayOfWeek) + "," + service.firstLetterUpper(month) + " " + String.valueOf(dayOfMonth));

    }

//set Main
    public void setMain(Main main){
        this.main = main;
        completedLabel.setVisible(main.getCompletedTasks().size() > 0);
        completedList.setVisible(main.getCompletedTasks().size() > 0);

        main.getTasksList().clear();
        main.getCompletedTasks().clear();
        main.getTasksData().stream().filter(task -> !task.isCompleted() && task.getDate().equals(LocalDate.now())).forEach(e->main.getTasksList().add(service.Maker(e,tasksList,completedList)));
        main.getTasksData().stream().filter(task -> task.isCompleted() && task.getDate().equals(LocalDate.now())).forEach(e->main.getCompletedTasks().add(service.Maker(e,tasksList,completedList)));

        tasksList.setItems(main.getTasksList());
        completedList.setItems(main.getCompletedTasks());

    }

//Handle Add button
    public void handleBtn(){

        if (validations.isInputValidTodayPage(todayTaskField)){
        String title = todayTaskField.getText();
        LocalDate date = LocalDate.now();

        Task newTask = new Task(title,"task" , false ,date);
        main.getTasksData().add(newTask);
        main.getTasksList().add(service.Maker(newTask , tasksList,completedList));

        taskDao.writeTask(newTask);

        main.getNumbers().set(0,(main.getNumbers().get(0)+1));

        System.out.println(main.getNumbers().get(0));
        }

        main.getTasksData().forEach(task -> System.out.println(task.isCompleted()));
        todayTaskField.setText("");
    }
}
