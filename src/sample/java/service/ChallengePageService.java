package sample.java.service;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import sample.Main;
import sample.java.dao.ChallengesDao;
import sample.java.dao.TaskDao;
import sample.java.model.Challenge;

import java.time.LocalDate;
import java.util.Optional;

public class ChallengePageService {

    public Stage dialogStage;
    public Main main;

    ListView<BorderPane> listView;

    //Зорилтууд хуудасанд мэдээлэл харуулах
    public BorderPane challengeRowMaker(Challenge challenge , ListView<BorderPane> challengesList , Main main) {

        this.listView = challengesList;
        this.main = main;

        BorderPane rootPane = new BorderPane();
        rootPane.setId("challengeRow");

        Label description = new Label(challenge.getDescription());
        Label endDate = new Label("Дуусах огноо:" + LocalDate.now().plusDays(challenge.getDuration()).toString());
        Label startedDate = new Label("Эхэлсэн огноо:" +  challenge.getStartedDate().toString());

        //Устгах товчлуурны event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Optional<ButtonType> result = Alerts.deleteAlert(dialogStage,"устгах" + challenge.getTitle()).showAndWait();
                if (result.orElse(ButtonType.CANCEL) == ButtonType.OK){

                    ChallengesDao.deleteChallenge(challenge);
                    TaskDao.deleteTask(challenge.getTitle());

                    main.getTasksData().removeIf(task -> task.getTitle().contains(challenge.getTitle()));

                    listView.getItems().remove(rootPane);
                    main.getChallengesData().remove(challenge);
                }
            }
        };

        //Устгах товчлуур
        Button deleteBtn = new Button("устгах");
        deleteBtn.setId("deleteBtn");
        deleteBtn.setOnAction(event);

        // Challenge мөрийн эх бие
        BorderPane cardContext = new BorderPane();
        cardContext.setLeft(startedDate);
        cardContext.setCenter(description);
        cardContext.setRight(endDate);

        // Challenge мөрийн гарчгийн хэсэг
        TitledPane challengeCard = new TitledPane();
        challengeCard.setText(challenge.getTitle());
        challengeCard.setContent(cardContext);
        challengeCard.setExpanded(false);
        challengeCard.setPrefHeight(40);

        rootPane.setRight(deleteBtn);
        rootPane.setCenter(challengeCard);

        return rootPane;
    }

}
