package sample.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import sample.Main;

import java.awt.*;

import static java.awt.Color.GREEN;
import static java.awt.Color.PINK;


public class RootLayoutController {

    public Main main;
    public Stage stage;

    //Set main
    public void setMain(Main main){
        this.main = main;
    }
    //Set Stage
    public void setStage(Stage stage){
        this.stage = stage;
    }
    @FXML
    private AnchorPane anchorPane;

    BackgroundFill backgroundFill = new BackgroundFill(javafx.scene.paint.Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
    Background background = new Background(backgroundFill);

    @FXML
    void changeColor(MouseEvent event) {
        anchorPane.setBackground(background);
    }
}

