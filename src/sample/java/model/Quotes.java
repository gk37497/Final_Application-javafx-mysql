package sample.java.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Quotes {
    ObservableList<String> quotes = FXCollections.observableArrayList(
            "Everything you can imagine is real. – Pablo Picasso",
            "Every moment is a fresh beginning. – T.S Eliot",
            "Never regret anything that made you smile. – Mark Twain",
            "Die with memories, not dreams. – Unknown "
    );

    public ObservableList<String> getQuotes() { return quotes; }
}
