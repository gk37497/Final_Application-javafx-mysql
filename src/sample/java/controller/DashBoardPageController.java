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

        int _iTodayTasks = FilteredLists.todayTasks(main).size();
        int _iAllTasks = filteredLists.thisWeekTasks(main).size();
        int _iTodayCompletedTasks = FilteredLists.todayCompletedTasks(main).size();
        int _iAllCompletedTasks = FilteredLists.completedTasks(main).size();

        numberOfTodayTasks.setText(String.valueOf(_iTodayTasks));
        numberOfAllTasks.setText(String.valueOf(_iAllTasks));

        numOfTdComTasks.setText(String.valueOf(_iTodayCompletedTasks));
        numOfAllComTasks.setText(String.valueOf(_iAllCompletedTasks));

        todayProgress.setProgress( (double) _iTodayCompletedTasks / _iTodayTasks);
        allTasksProgress.setProgress((double) _iAllCompletedTasks / _iAllTasks);

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

        int _iSucDay = FilteredLists.successTasksOfChallenge(main,challenge).size();
        int _iUnSucDay = FilteredLists.unsuccessfulTasksOfChallenge(main,challenge).size();

        successDay.setText(String.valueOf(_iSucDay));
        unSuccessDay.setText(String.valueOf(_iUnSucDay));

        challengeProgress.setProgress((double) _iSucDay / challenge.getDuration());
    }
}
