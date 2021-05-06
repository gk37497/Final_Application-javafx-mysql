package sample.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.Main;
import sample.java.dao.TaskDao;
import sample.java.model.Task;
import sample.java.service.FilteredLists;
import sample.java.service.TaskPagesService;
import sample.java.service.Validations;

import java.time.LocalDate;

public class TaskPageController {

    public Main main;
    public Stage dialogStage;
    public TaskPagesService service = new TaskPagesService();
    public Validations validations = new Validations();
    public FilteredLists filteredLists = new FilteredLists();

    @FXML
    public ListView<BorderPane> tasksList;

    @FXML
    private ListView<BorderPane> completedList;

    @FXML
    private TextField taskTitleField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label completedLabel;


    //Set main
    public void setMain(Main main){
        this.main = main;

        main.getTasksList().clear();
        main.getCompletedTasks().clear();

        /* Бүх биелэгдээгүй task-ийг service-ийн maker-ээр дамжуулан borderpane болгож дэлгэцэнд хуруулах. */
        filteredLists.thisWeekTasks(this.main).filtered(task ->!task.isCompleted()).forEach(e->main.getTasksList().add(service.maker(e , tasksList, completedList, main)));

        /* Бүх биелэгдсэн task-ийг service-ийн maker-ээр дамжуулан borderpane болгож дэлгэцэнд хуруулах. */
        FilteredLists.completedTasks(this.main).forEach(e->main.getCompletedTasks().add(service.maker(e,tasksList,completedList,main)));

        tasksList.setItems(main.getTasksList());
        completedList.setItems(main.getCompletedTasks());

        completedList.setVisible(main.getCompletedTasks().size() > 0);
        completedLabel.setVisible(main.getCompletedTasks().size() > 0);
    }
    //Set dialog
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    //Нэмэх товчлуур дарагдах үед
    public void handleBtn(){

        if (validations.taskPage(taskTitleField , datePicker)) {

            String _strTitle =  taskTitleField.getText();
            LocalDate date = datePicker.getValue();
            Task newTask = new Task(_strTitle, "task" , false, date);

            /* шинэ task-ийг өгөгдлийн сан руу нэмж дэлгэцэнд харуулах. */
            service.addTask(main,newTask,tasksList,completedList);

        }

        taskTitleField.setText("");
        taskTitleField.setFocusTraversable(true);
    }

}
