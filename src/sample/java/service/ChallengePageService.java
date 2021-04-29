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
import sample.java.model.Challenge;



public class ChallengePageService {

    public Stage dialogStage;

    ListView<BorderPane> listView;
    public BorderPane maker(Challenge newChallenge , ListView<BorderPane> challengesList) {

        this.listView = challengesList;
        TitledPane titledPane = new TitledPane();
        BorderPane borderPane = new BorderPane();
        BorderPane rootPane = new BorderPane();

        Button delete = new Button("Delete");

        Label label = new Label(newChallenge.getDescription());
        Label label1 = new Label(newChallenge.getTitle());
        titledPane.setText(newChallenge.getTitle());

        rootPane.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#ffffff"), CornerRadii.EMPTY, Insets.EMPTY)));
        delete.setStyle("-fx-background-color : null;" +
                "-fx-text-fill : red");


//delete button event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                listView.getItems().remove(rootPane);
            }
        };

        delete.setOnAction(event);

        borderPane.setLeft(label1);
        borderPane.setCenter(label);

        titledPane.setContent(borderPane);
        titledPane.setExpanded(false);

        rootPane.setRight(delete);
        rootPane.setCenter(titledPane);

        return rootPane;
    }

}
