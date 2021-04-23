package sample.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import sample.Main;
import sample.java.model.Challenge;
import sample.java.service.ChallengePageService;

import java.time.LocalDate;

public class ChallengePageController {

    @FXML
    private ListView<BorderPane> challengesList;
    public Main main;
    public ChallengePageService service = new ChallengePageService();



    @FXML
    void initialize(){
//        challengesList.setCellFactory(new Callback<ListView<BorderPane>, ListCell<BorderPane>>() {
//            @Override
//            public ListCell<BorderPane> call(ListView<BorderPane> param) {
//                return new ListCell<BorderPane>();
//            }
//        });
    }
    public void setMain(Main main){
        this.main = main;
        main.getChallengeList().clear();
        main.getChallengesData().filtered(challenge -> challenge.getType().equals("active")).forEach(challenge -> main.getChallengeList().add(service.maker(challenge , challengesList)));
        challengesList.setItems(main.getChallengeList());
        main.checkChallenge();
    }

    @FXML
    void handleDeleteBtn(ActionEvent event) {
        int selectedIndex = challengesList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0){
            main.getChallengesData().remove(selectedIndex);
            challengesList.getItems().remove(selectedIndex);
        }
    }

    @FXML
    void handleAddBtn(ActionEvent event ) {
        Challenge newChallenge = new Challenge("learn python" , "good" , LocalDate.now(),30 , "active");
        main.getChallengesData().add(newChallenge);
        main.getChallengeList().add(service.maker(newChallenge,challengesList));
        System.out.println(main.getChallengesData().size());
        main.getChallengesData().forEach(challenge -> System.out.println(challenge.getType()));
    }
}
