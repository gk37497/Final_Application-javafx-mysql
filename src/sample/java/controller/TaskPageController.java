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
        completedList.setVisible(main.getCompletedTasks().size() > 0);
        completedLabel.setVisible(main.getCompletedTasks().size() > 0);

        this.main = main;
        main.getTasksList().clear();
        main.getCompletedTasks().clear();

        FilteredLists.tasks(this.main).forEach(e->main.getTasksList().add(service.maker(e , tasksList, completedList)));
        FilteredLists.completedTasks(this.main).forEach(e->main.getCompletedTasks().add(service.maker(e,tasksList,completedList)));

        tasksList.setItems(main.getTasksList());
        completedList.setItems(main.getCompletedTasks());
    }
    //Set dialog
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    //handle add button
    public void handleBtn(){

        if (validations.taskPage(taskTitleField , datePicker)) {

            String title =  taskTitleField.getText();
            LocalDate date = datePicker.getValue();

            Task newTask = new Task(title, "task" , false, date);
            main.getTasksData().add(newTask);
            main.getTasksList().add(service.maker(newTask , tasksList , completedList));

            TaskDao.writeTask(newTask);
        }

        taskTitleField.setText("");
        taskTitleField.setFocusTraversable(true);
    }

}
