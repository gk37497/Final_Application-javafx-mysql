package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.java.controller.*;
import sample.java.dao.ChallengesDao;
import sample.java.dao.TaskDao;
import sample.java.model.Challenge;
import sample.java.model.Task;
import sample.java.service.FilteredLists;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.TemporalAmount;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;


    //Tasks and Challenges data lists
    private final ObservableList<Task> tasksData =  FXCollections.observableArrayList();
    private final ObservableList<Challenge> challengesData = FXCollections.observableArrayList();

    //display pane lists
    private final ObservableList<BorderPane> tasksList = FXCollections.observableArrayList();
    private final ObservableList<BorderPane> completedTasks = FXCollections.observableArrayList();
    private final ObservableList<BorderPane> challengeList = FXCollections.observableArrayList();


    public Main() throws SQLException, ClassNotFoundException {

        getTasksData().addAll(TaskDao.getTasksList());
        getChallengesData().addAll(ChallengesDao.getChallengesList());
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
        } catch(IOException | SQLException | ClassNotFoundException e) {
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
            BorderPane challengePage = (BorderPane) loader.load();

            ChallengePageController controller = loader.getController();
            controller.setMain(this);
            controller.setDialogStage(primaryStage);
            rootLayout.setCenter(challengePage);

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
            controller.setMain(this);
            rootLayout.setCenter(taskPage);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /*
     * Show the New Challenge dialog.
    */

    public Boolean showNewChallengeDialog(Challenge newChallenge , String title){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("resources/view/NewChallengeDialog.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Challenge");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NewChallengeDialogController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setChallenge(newChallenge,title);
            controller.setMain(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) { launch(args); }
}
