package sample.java.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Task {
    private final StringProperty title;
    private final StringProperty type;
    private final ObjectProperty<LocalDate> date;

    public Task(){
        this(null,null,null);
    };

    public Task(String title, String type, LocalDate date) {
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleStringProperty(type);
        this.date = new SimpleObjectProperty<LocalDate>(date);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
