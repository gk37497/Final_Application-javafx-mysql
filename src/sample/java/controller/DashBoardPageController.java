package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import sample.Main;
import sample.java.model.Challenge;
import sample.java.service.FilteredLists;

public class DashBoardPageController {

    public Main main;
    public FilteredLists filteredLists = new FilteredLists();

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
    private Label successDay;

    @FXML
    private Label unSuccessDay;

    @FXML
    private Label duration;

    @FXML
    private Label challengeTitle;

    //Set main
    public void setMain(Main main) {
        this.main = main;
        showStatistics();
        createScrollPane();
    }
    //Статистикуудыг дэлгэцэнд харуулах
    public void showStatistics(){

        int todayTasks = FilteredLists.todayTasks(main).size();
        int allTasks = filteredLists.thisWeekTasks(main).size();
        int todayCompletedTasks = FilteredLists.todayCompletedTasks(main).size();
        int allCompletedTasks = FilteredLists.completedTasks(main).size();

        numberOfTodayTasks.setText(String.valueOf(todayTasks));
        numberOfAllTasks.setText(String.valueOf(allTasks));

        numOfTdComTasks.setText(String.valueOf(todayCompletedTasks));
        numOfAllComTasks.setText(String.valueOf(allCompletedTasks));

        todayProgress.setProgress( (double) todayCompletedTasks / todayTasks);
        allTasksProgress.setProgress((double) allCompletedTasks / allTasks);

    }
    //Challenge ийн нэртэй товчлуурууд агуулсан scroll pane
    public void createScrollPane(){
        HBox hBox = new HBox(10);
        main.getChallengesData().forEach(challenge -> hBox.getChildren().add(challengeBtn(challenge)));
        challengesName.setContent(hBox);
    }

    //Challenge ийн нэртэй товчлуур үүсгэх
    public Button challengeBtn(Challenge challenge){

        Button button = new Button();
        button.setText(challenge.getTitle());

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                challengeBtnClicked(challenge);
            }
        };

        button.setOnAction(event);
        button.setId("challengeBtn");
        return button;
    }

    // Challenge ийн нэртэй товчуул дарагдах үед
    public void challengeBtnClicked(Challenge challenge){

        challengeTitle.setText(challenge.getTitle());
        duration.setText(String.valueOf(challenge.getDuration()));

        int sucDay = FilteredLists.successTasksOfChallenge(main,challenge).size();
        int unSucDay = FilteredLists.unsuccessfulTasksOfChallenge(main,challenge).size();

        successDay.setText(String.valueOf(sucDay));
        unSuccessDay.setText(String.valueOf(unSucDay));

        challengeProgress.setProgress((double) sucDay / challenge.getDuration());
    }
}
