package sample.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.Main;
import sample.java.model.Task;
import sample.java.service.TaskPagesService;

import java.time.LocalDate;

public class TaskPageController {

    public Main main;
    public Stage dialogStage;
    public TaskPagesService service = new TaskPagesService();

    @FXML
    public ListView<BorderPane> tasksList;

    @FXML
    private ListView<BorderPane> completedList;

    @FXML
    void initialize(){
    }
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
        main.getTasksData().filtered(task -> task.getType().equals("active")).forEach(e->main.getTasksList().add(service.Maker(e , tasksList, completedList)));
        main.getTasksData().filtered(task -> task.getType().equals("completed")).forEach(e->main.getCompletedTasks().add(service.Maker(e,tasksList,completedList)));
        tasksList.setItems(main.getTasksList());
        completedList.setItems(main.getCompletedTasks());
    }
    //Set dialog
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    //handle add button
    public void handleBtn(){

        if (service.isInputValidTasksPage(taskTitleField , datePicker)) {
            String title =  taskTitleField.getText();
            LocalDate date = datePicker.getValue();


            Task newTask = new Task(title, "active", date);
            main.getTasksData().add(newTask);
            main.getTasksList().add(service.Maker(newTask , tasksList , completedList));
        }

        taskTitleField.setText("");
    }

}
