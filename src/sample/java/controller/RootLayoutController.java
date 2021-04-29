package sample.java.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import sample.Main;
import sample.java.dao.taskDao;
import sample.java.model.Challenge;
import sample.java.model.Task;
import sample.java.service.Constants;

import java.sql.SQLException;
import java.time.LocalDate;

public class RootLayoutController {

    public Main main;
    public Stage stage;
    public Constants constants = new Constants();

    @FXML
    private AnchorPane todayBtn;

    @FXML
    private AnchorPane challengesBtn;

    @FXML
    private AnchorPane taskBtn;

    @FXML
    private AnchorPane weekScheduleBtn;

    @FXML
    private AnchorPane dashBoardBtn;

    @FXML
    private ImageView todayIcon;

    @FXML
    private Label numberOfTodayTask;

    @FXML
    private Label numberOfChallenges;

    @FXML
    private Label numberOfAllTasks;

//Set main
    public void setMain(Main main) throws SQLException,ClassNotFoundException{
        this.main = main;

        backgroundChanger(constants.getPageNames()[0]);
        main.getChallengesData().forEach(this::convertChallenge);

        numberOfTodayTask.setText(String.valueOf(main.getNumbers().get(0)));
        numberOfAllTasks.setText(String.valueOf(main.getNumbers().get(1)));
        numberOfChallenges.setText(String.valueOf(main.getChallengesData().size()));

        main.getTasksData().addAll(taskDao.getEmployeeList());
    }

//Set Stage
    public void setStage(Stage stage){
        this.stage = stage;
    }


//show TASK page
    @FXML
    void showTaskPage(MouseEvent event) {
        main.showTaskPage();
        backgroundChanger(constants.getPageNames()[1]);
    }

//show TODAY page
    @FXML
    void showTodayPage(MouseEvent event) {
        main.showTodayPage();
        backgroundChanger(constants.getPageNames()[0]);
    }
//show CHALLENGE page
    @FXML
    void showChallengePage(MouseEvent event) {
        main.showChallengePage();
        backgroundChanger(constants.getPageNames()[2]);
    }
//show DASHBOARD page
    @FXML
    void showDashboardPage(MouseEvent event) {
        main.showDashboardPage();
        backgroundChanger(constants.getPageNames()[3]);
    }
//show WEEK SCHEDULE page
    @FXML
    void showWeekSchedulePage(MouseEvent event) {
        main.showWeekSchedulePage();
        backgroundChanger(constants.getPageNames()[4]);
    }


//Convert challenges to task
    public void convertChallenge(Challenge challenge){

//    ObservableList<Challenge> challenges = main.getChallengesData();
    int duration = challenge.getDuration();
    String title = challenge.getTitle();
    for (int i = 0; i < duration; i++) {
        main.getTasksData().add(new Task(title,"challenge",false, LocalDate.now().plusDays(i)));
    }
}


// Background maker
    public Background backgroundMaker(String s){
        BackgroundFill backgroundFill = new BackgroundFill(javafx.scene.paint.Color.web(s), CornerRadii.EMPTY, Insets.EMPTY);
        return new Background(backgroundFill);
    }
    //Pages backgrounds
    public void backgroundChanger(String value){
        taskBtn.setBackground(value.equals(constants.getPageNames()[1]) ? backgroundMaker(constants.getTaskPageColor()) : Background.EMPTY);
        todayBtn.setBackground(value.equals(constants.getPageNames()[0]) ? backgroundMaker(constants.getTodayPageColor()) : Background.EMPTY);
        challengesBtn.setBackground(value.equals(constants.getPageNames()[2]) ? backgroundMaker(constants.getChallengePageColor()) : Background.EMPTY);
        weekScheduleBtn.setBackground(value.equals(constants.getPageNames()[4]) ? backgroundMaker(constants.getWeekSchedulePageColor()) : Background.EMPTY);
        dashBoardBtn.setBackground(value.equals(constants.getPageNames()[3]) ? backgroundMaker(constants.getDashBoardPageColor()) : Background.EMPTY);
    }

}

