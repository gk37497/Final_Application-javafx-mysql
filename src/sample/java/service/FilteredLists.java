package sample.java.service;

import javafx.collections.ObservableList;
import sample.Main;
import sample.java.model.Challenge;
import sample.java.model.Task;

import java.time.LocalDate;
import java.util.Comparator;

public class FilteredLists {

    //Өнөөдрийн биелэгдээгүй таскууд агуулсан лист
    public static ObservableList<Task> todayUncompletedTasks(Main main){
        return main.getTasksData().filtered(task -> !task.isCompleted() && task.getDate().equals(LocalDate.now()));
    }

    //Өнөөдрийн биелэгдсэн таскууд агуулсан лист
    public static ObservableList<Task> todayCompletedTasks(Main main){
        return main.getTasksData().filtered(task -> task.isCompleted() && task.getDate().equals(LocalDate.now()));
    }

    //Бүх биелэгдсэн таскууд агуулсан лист
    public static ObservableList<Task> completedTasks(Main main){
        return main.getTasksData().filtered(Task :: isCompleted);
    }

    //Тухайн 7 хоногын гариг дахь таскуудыг ялгаж байна
    public ObservableList<Task> tasksOfDay(Main main , String dayOfWeek){
        ObservableList <Task> thisWeekTasks = main.getTasksData().filtered(this::isDayOfWeek);
        return thisWeekTasks.filtered(task -> task.getDate().getDayOfWeek().toString().equals(dayOfWeek));
    }
    //Өнөөдрийн бүх таск
    public static  ObservableList<Task> todayTasks(Main main){
        return main.getTasksData().filtered(task -> task.getDate().equals(LocalDate.now()));
    }
    //Энэ 7 хоногийн таскууд
    public ObservableList<Task> thisWeekTasks(Main main){
        return main.getTasksData().filtered(task -> task.getDate().getDayOfYear() <= getSunday()
                && task.getDate().getDayOfYear() >= getMonday())
                .sorted(Comparator.comparing(Task::getDate));
    }

    //Зорилт биелүүлэгдсэн таскууд
    public static ObservableList<Task> successTasksOfChallenge(Main main , Challenge challenge){
        return main.getTasksData().filtered(task -> task.getType().equals("challenge")
                && task.isCompleted()
                && task.getTitle().contains(challenge.getTitle()));

    }

    public static ObservableList<Task> unsuccessfulTasksOfChallenge(Main main , Challenge challenge){
        return main.getTasksData().filtered(task -> task.getType().equals("challenge")
                && !task.isCompleted()
                && task.getTitle().contains(challenge.getTitle())
                && task.getDate().isBefore(LocalDate.now())
        );

    }
    //Энэ 7 хоногийн Даваа гаригийг олж байна
    public int getMonday(){
        int i = 0;
        while(true){
            if (LocalDate.now().minusDays(i).getDayOfWeek().toString().equals("MONDAY")){
                return LocalDate.now().minusDays(i).getDayOfYear();
            }
            else{
                i++;
            }
        }
    }

    //Энэ 7 хоногийн Ням гаригийг олж байна
    public int getSunday(){
        int i = 0;
        while(true){
            if (LocalDate.now().plusDays(i).getDayOfWeek().toString().equals("SUNDAY")){
                return  LocalDate.now().plusDays(i).getDayOfYear();
            }
            else{
                i++;
            }
        }
    }

    //Энэ 7 хоногт байдаг өдөр эсэхийг шалгаж байна
    public Boolean isDayOfWeek(Task task){
        int d = task.getDate().getDayOfYear();
        return getMonday() <= d && d <= getSunday();
    }
}
