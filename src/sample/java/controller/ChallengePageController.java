package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Main;
import sample.java.model.Challenge;
import sample.java.service.ChallengePageService;
import sample.java.service.Validations;

public class ChallengePageController {


    public Main main;
    public Stage dialogStage;

    public ChallengePageService service = new ChallengePageService();
    public Validations validations = new Validations();

    @FXML
    private ListView<BorderPane> challengesList;

    @FXML
    private TextField challengeTitleField;

    @FXML
    void initialize(){


    }
    //set Main
    public void setMain(Main main){

        this.main = main;
        main.getChallengeList().clear();
        main.getChallengesData()
                .filtered(challenge -> challenge.getType()
                        .equals("active"))
                .forEach(challenge -> main.getChallengeList()
                        .add(service.maker(
                                challenge ,
                                challengesList
                        )));
        challengesList.setItems(main.getChallengeList());
        main.checkChallenge();
    }

    //Set Stage
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //handle Add button
    @FXML
    void handleAddBtn(ActionEvent event ) {

        if(validations.isInputValidChallengesPage(challengeTitleField)){

            Challenge newChallenge = new Challenge();
            Boolean okClicked = main.showNewChallengeDialog(newChallenge , challengeTitleField.getText());

            if (okClicked){
                main.getChallengesData().add(newChallenge);
                main.getChallengeList().add(service.maker(newChallenge,challengesList));
            }
        }
        System.out.print( challengeTitleField.getText() + main.getTasksData().filtered(task -> task.getType().equals("challenge") && task.getTitle().equals(challengeTitleField.getText()) && task.isCompleted()).size() + "\n");

    }
}
