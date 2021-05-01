package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Main;
import sample.java.dao.ChallengesDao;
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

    //set Main
    public void setMain(Main main){

        this.main = main;
        main.getChallengeList().clear();
        main.getChallengesData().forEach(challenge -> main.getChallengeList().add(service.maker(challenge , challengesList, this.main)));

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

        if(validations.challengePage(challengeTitleField)){
            Challenge newChallenge = new Challenge();
            Boolean okClicked = main.showNewChallengeDialog(newChallenge , challengeTitleField.getText());

            if (okClicked){
                main.getChallengesData().add(newChallenge);
                main.getChallengeList().add(service.maker(newChallenge, challengesList, this.main));
                ChallengesDao.writeChallenge(newChallenge);
            }
        }
    }
}
