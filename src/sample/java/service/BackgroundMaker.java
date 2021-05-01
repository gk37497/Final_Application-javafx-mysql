package sample.java.service;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;

public class BackgroundMaker {
    public static Background maker(String s){
        BackgroundFill backgroundFill = new BackgroundFill(javafx.scene.paint.Color.web(s), CornerRadii.EMPTY, Insets.EMPTY);
        return new Background(backgroundFill);
    }
}
