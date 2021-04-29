package sample.java.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Task {

    //Declare variables
    private final StringProperty title;
    private final StringProperty type;
    private final BooleanProperty Completed;
    private final ObjectProperty<LocalDate> date;

    //Default constructor
    public Task(){
        this(null,null,false ,null);
    };

    //Constructor with parameters
    public Task(String title, String type , boolean Completed, LocalDate date) {
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleObjectProperty<LocalDate>(date);
        this.Completed = new SimpleBooleanProperty(Completed);
    }


    //Getter setter
    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public LocalDate getDate() { return date.get(); }

    public ObjectProperty<LocalDate> dateProperty() { return date; }

    public void setDate(LocalDate date) { this.date.set(date); }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public boolean isCompleted() {
        return Completed.get();
    }

    public BooleanProperty completedProperty() {
        return Completed;
    }

    public void setCompleted(boolean completed) {
        this.Completed.set(completed);
    }
}
