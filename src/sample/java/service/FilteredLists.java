package sample.java.service;

import javafx.collections.ObservableList;
import sample.Main;
import sample.java.model.Task;

import java.time.LocalDate;

public class FilteredLists {

    //Өнөөдрийн биелэгдээгүй таскууд агуулсан лист
    public static ObservableList<Task> todayTasks(Main main){
        return main.getTasksData().filtered(task -> !task.isCompleted() && task.getDate().equals(LocalDate.now()));
    }

    //Өнөөдрийн биелэгдсэн таскууд агуулсан лист
    public static ObservableList<Task> todayCompletedTasks(Main main){
        return main.getTasksData().filtered(task -> task.isCompleted() && task.getDate().equals(LocalDate.now()));
    }

    //Бүх биелэгдээгүй таскууд агуулсан лист
    public static ObservableList<Task> tasks(Main main){
        return main.getTasksData().filtered(task -> !task.isCompleted() && task.getType().equals("task"));
    }

    //Бүх биелэгдсэн таскууд агуулсан лист
    public static ObservableList<Task> completedTasks(Main main){
        return main.getTasksData().filtered(Task :: isCompleted);
    }

    //Тухайн 7 хоног дахь таскуудыг ялгаж байна
    public  ObservableList<Task> tasksOfThisWeek(Main main , String dayOfWeek){
        ObservableList <Task> intervalDay = main.getTasksData().filtered(this::isDayOfWeek);
        return intervalDay.filtered(task -> task.getDate().getDayOfWeek().toString().equals(dayOfWeek));
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
