package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.java.controller.*;
import sample.java.model.Challenge;
import sample.java.model.Task;

import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private final ObservableList<Task> tasksData =  FXCollections.observableArrayList();
    private final ObservableList<BorderPane> tasksList = FXCollections.observableArrayList();
    private final ObservableList<BorderPane> completedTasks = FXCollections.observableArrayList();
    private final ObservableList<Challenge> challengesData = FXCollections.observableArrayList();
    private final ObservableList<BorderPane> challengeList = FXCollections.observableArrayList();


    public Main(){
        tasksData.add(new Task("sleep","active", LocalDate.now()));
        tasksData.add(new Task("eat","active", LocalDate.now()));
        tasksData.add(new Task("walk","active", LocalDate.now()));
    }

    public ObservableList<BorderPane> getCompletedTasks() { return completedTasks; }

    public ObservableList<BorderPane> getChallengeList() { return challengeList; }

    public ObservableList<Task> getTasksData() { return tasksData; }

    public ObservableList<BorderPane> getTasksList(){ return tasksList; }

    public ObservableList<Challenge> getChallengesData() {
        checkChallenge();
        return challengesData;
    }

    public void checkChallenge(){
        challengesData.removeIf(challenge -> challenge.getType().equals("deleted"));
    }

    @Override
    public void start(Stage primaryStage){

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("To do list app");

        initRootLayout();
        showTodayPage();
    }

    /**
     * Initializes the root layout.
     */

    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class
                    .getResource("resources/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            controller.setStage(primaryStage);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Show the Today page inside the root layout.
    */

    public void showTodayPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/view/TodayPage.fxml"));
            BorderPane todayPage = (BorderPane) loader.load();
            rootLayout.setCenter(todayPage);
            TodayPageController controller = loader.getController();
            controller.setMain(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * Show the Task page inside the root layout.
    */
    public void showTaskPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/view/TaskPage.fxml"));
            BorderPane taskPage = (BorderPane) loader.load();
            TaskPageController controller = loader.getController();
            controller.setMain(this);
            controller.setDialogStage(this.primaryStage);
            rootLayout.setCenter(taskPage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * Show the Challenge page inside the root layout.
    */
    public void showChallengePage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/view/ChallengePage.fxml"));
            BorderPane taskPage = (BorderPane) loader.load();

            ChallengePageController controller = loader.getController();
            controller.setMain(this);
            rootLayout.setCenter(taskPage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*
     * Show the Week Schedule page inside the root layout.
    */
    public void showWeekSchedulePage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/view/WeekSchedulePage.fxml"));
            BorderPane taskPage = (BorderPane) loader.load();

            WeekSchedulePageController controller = loader.getController();
            controller.setMain(this);
            rootLayout.setCenter(taskPage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /*
     * Show the Dashboard page inside the root layout.
    */

    public void showDashboardPage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/view/DashBoardPage.fxml"));
            BorderPane taskPage = (BorderPane) loader.load();

            DashBoardPageController controller = loader.getController();
            rootLayout.setCenter(taskPage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) { launch(args); }
}
