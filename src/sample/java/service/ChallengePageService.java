package sample.java.service;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import sample.java.model.Challenge;

public class ChallengePageService {

    ListView<BorderPane> listView;
    public BorderPane maker(Challenge newChallenge , ListView<BorderPane> challengesList) {

        this.listView = challengesList;
        BorderPane borderPane = new BorderPane();
        CheckBox checkBox = new CheckBox();
        System.out.print("\nid = " + listView.getItems().size() + "\n");
        int id = listView.getItems().size();

        checkBox.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
                    listView.getSelectionModel().select(id);
                });

        Label label = new Label("Mongolia is the most powerful country in the world");
        Label label1 = new Label(newChallenge.getTitle());
        borderPane.setLeft(checkBox);
        borderPane.setRight(label1);
        borderPane.setCenter(label);

        return borderPane;
    }

}
