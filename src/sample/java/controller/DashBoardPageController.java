package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import sample.Main;
import sample.java.model.Challenge;
import sample.java.model.Task;


import java.time.LocalDate;

public class DashBoardPageController {

    public Main main;

    @FXML
    private Label numberOfTodayTasks;

    @FXML
    private Label numOfTdComTasks;

    @FXML
    private Label numberOfAllTasks;

    @FXML
    private Label numOfAllComTasks;

    @FXML
    private ProgressIndicator todayProgress;

    @FXML
    private ProgressIndicator allTasksProgress;

    @FXML
    private ScrollPane challengesName;

    @FXML
    private ProgressIndicator challengeProgress;

    @FXML
    private TextField successDay;

    @FXML
    private TextField unSuccessDay;

    @FXML
    private TextField duration;

    @FXML
    private Label challengeTitle;

    //set main
    public void setMain(Main main) {
        this.main = main;
        showStatistics();
        createScrollPane();
    }

    public void showStatistics(){
        //filtered lists class baiguulaad tendees duudah
        int n = main.getTasksData().filtered(task -> task.getDate().equals(LocalDate.now())).size();
        int m = main.getTasksData().filtered(task -> !task.getDate().equals(LocalDate.now())).size();
        int n1 = main.getTasksData().filtered(task -> task.isCompleted() && task.getDate().equals(LocalDate.now())).size();
        int m1 = main.getTasksData().filtered(Task::isCompleted).size();

        numberOfTodayTasks.setText(String.valueOf(n));
        numberOfAllTasks.setText(String.valueOf(m));

        numOfTdComTasks.setText(String.valueOf(n1));
        numOfAllComTasks.setText(String.valueOf(m1));

        todayProgress.setProgress( (double) n1 / n);
        allTasksProgress.setProgress((double) m1 / m);
    }

    public void createScrollPane(){
        HBox hBox = new HBox(10);
        main.getChallengesData().forEach(challenge -> hBox.getChildren().add(challengeNameGenerator(challenge)));
        challengesName.setContent(hBox);
    }

    public Button challengeNameGenerator(Challenge challenge){

        Button button = new Button();
        button.setText(challenge.getTitle());

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                challengeBtnClicked(challenge);
            }
        };
        button.setOnAction(event);

        return button;
    }

    public void challengeBtnClicked(Challenge challenge){

        challengeTitle.setText(challenge.getTitle());
        duration.setText(String.valueOf(challenge.getDuration()));

//bas eniig
        int sucDay = main.getTasksData().filtered(task -> task.getType().equals("challenge") && task.isCompleted() && task.getTitle().equals(challenge.getTitle())).size();
        int unSucDay = main.getTasksData().filtered(task -> task.getType().equals("challenge") && !task.isCompleted()).size();

        successDay.setText(String.valueOf(sucDay));
        unSuccessDay.setText(String.valueOf(unSucDay));

        challengeProgress.setProgress((double) sucDay / challenge.getDuration());
    }
}
