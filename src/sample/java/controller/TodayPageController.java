package sample.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import sample.Main;
import sample.java.dao.TaskDao;
import sample.java.model.Task;
import sample.java.service.FilteredLists;
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
    private TextField addTaskField;

    @FXML
    private ListView<BorderPane> completedList;

    @FXML
    private Label completedLabel;


//initialize
    @FXML
    void initialize(){
        subTitle.setText(TaskPagesService.dateConverter(LocalDate.now()));
    }

//set Main
    public void setMain(Main main){
        this.main = main;

        main.getTasksList().clear();
        main.getCompletedTasks().clear();

        FilteredLists.todayTasks(this.main).forEach(e->main.getTasksList().add(service.maker(e,tasksList,completedList)));
        FilteredLists.todayCompletedTasks(this.main).forEach(e->main.getCompletedTasks().add(service.maker(e,tasksList,completedList)));

        completedLabel.setVisible(main.getCompletedTasks().size() > 0);
        completedList.setVisible(main.getCompletedTasks().size() > 0);

        tasksList.setItems(main.getTasksList());
        completedList.setItems(main.getCompletedTasks());

    }

//Handle Add button
    public void handleBtn(){

        if (validations.todayPage(addTaskField)){
        String title = addTaskField.getText();
        LocalDate date = LocalDate.now();

        Task newTask = new Task(title,"task" , false ,date);
        main.getTasksData().add(newTask);
        main.getTasksList().add(service.maker(newTask , tasksList,completedList));

        TaskDao.writeTask(newTask);
        }
        addTaskField.setText("");
    }
}
