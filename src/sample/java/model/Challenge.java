package sample.java.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Challenge {
    final StringProperty title;
    final StringProperty description;
    final ObjectProperty<LocalDate> startedDate;
    final IntegerProperty duration;
    final StringProperty type;
    final BooleanProperty completed;

    //Default constructor
    public Challenge(){ this(null,null,null,0,null, false); }

    //Parameter constructor
    public Challenge(String title, String description, LocalDate startedDate, int duration, String type, boolean completed) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.startedDate = new SimpleObjectProperty<>(startedDate);
        this.duration = new SimpleIntegerProperty(duration);
        this.type = new SimpleStringProperty(type);
        this.completed = new SimpleBooleanProperty(completed);
    }

    public String getType() { return type.get(); }

    public StringProperty typeProperty() { return type; }

    public void setType(String type) { this.type.set(type); }

    public int getDuration() { return duration.get(); }

    public IntegerProperty durationProperty() { return duration; }

    public void setDuration(int duration) { this.duration.set(duration); }

    public LocalDate getStartedDate() { return startedDate.get(); }

    public ObjectProperty<LocalDate> startedDateProperty() { return startedDate; }

    public void setStartedDate(LocalDate startedDate) { this.startedDate.set(startedDate); }

    public String getDescription() { return description.get(); }

    public StringProperty descriptionProperty() { return description; }

    public void setDescription(String description) { this.description.set(description); }

    public String getTitle() { return title.get(); }

    public StringProperty titleProperty() { return title; }

    public boolean isCompleted() { return completed.get(); }

    public BooleanProperty completedProperty() { return completed; }

    public void setCompleted(boolean completed) { this.completed.set(completed); }

    public void setTitle(String title) { this.title.set(title); }
}
