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

        /* Task -ийн мэдээлэлтэй borderpane -ууд агуулсан ObservableList-ийг цэвэрлэх.*/
        main.getTasksList().clear();
        main.getCompletedTasks().clear();

        /* Өнөөдрийн бүх биелэгдээгүй task-ийг service-ийн maker-ээр дамжуулан borderpane болгож дэлгэцэнд хуруулах. */
        FilteredLists.todayUncompletedTasks(this.main).forEach(e->main.getTasksList().add(service.maker(e,tasksList,completedList , main)));

        /* Өнөөдрийн бүх биелэгдсэн task-ийг service-ийн maker-ээр дамжуулан borderpane болгож дэлгэцэнд хуруулах.*/
        FilteredLists.todayCompletedTasks(this.main).forEach(e->main.getCompletedTasks().add(service.maker(e,tasksList,completedList,main)));

        /* Хэрэв биелэгдсэн task байхгүй бол (Биелэгдсэн)-хэсгийг харагдахгүй байлгах.*/
        completedLabel.setVisible(main.getCompletedTasks().size() > 0);
        completedList.setVisible(main.getCompletedTasks().size() > 0);

        tasksList.setItems(main.getTasksList());
        completedList.setItems(main.getCompletedTasks());
    }

//Нэмэх товчлуур дарагдах үед
    public void handleBtn(){

        if (validations.todayPage(addTaskField)){

            String _strTitle = addTaskField.getText();
            LocalDate date = LocalDate.now();
            Task newTask = new Task(_strTitle,"task" , false ,date);

            /* шинэ task-ийг өгөгдлийн сан руу нэмж дэлгэцэнд харуулах. */
            service.addTask(main,newTask,tasksList,completedList);
        }
        addTaskField.setText("");
    }
}
