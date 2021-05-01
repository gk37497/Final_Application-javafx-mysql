package sample.java.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.java.model.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDao {

    //Бүх таскыг өгөгдлийн сангаас дуудах
    public static ObservableList<Task> getTasksList() throws SQLException{

        String selectStmt = "SELECT * FROM tasks";

        ResultSet rs = DbConnection.dbExecuteQuery(selectStmt);
        ObservableList<Task> tasksList = FXCollections.observableArrayList();

        while(rs.next()){
            Task task = new Task(
                    rs.getString("title"),
                    rs.getString("type"),
                    rs.getBoolean("completed"),
                    rs.getDate("date").toLocalDate()
            );
            tasksList.add(task);
        }
        return tasksList;
    }

    //Шинэ таск өгөдлийн сан руу нэмэх
    public static void writeTask(Task task){
        String insertStmt =
                "INSERT INTO tasks\n" +
                "VALUES('"+task.getTitle()+"','"+task.getDate()+"','"+task.getType()+"','"+(task.isCompleted() ? 1 : 0)+"\n')";
        try{
            DbConnection.dbExecuteUpdate(insertStmt);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Биелэгдсэн таскыг тэмдэглэх
    public static void updateCompletedCol(Task task){
        String updateCompletedCol =
                "UPDATE tasks\n" +
                        "SET completed = 1\n" +
                        "WHERE title = '" + task.getTitle() + "'\n";

        try{
            DbConnection.dbExecuteUpdate(updateCompletedCol);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Таск устгах
    public static void deleteTask(String title){
        String deleteStmt =
                "DELETE FROM tasks\n" +
                "WHERE title LIKE '"+ title +"%'\n";

        try{
            DbConnection.dbExecuteUpdate(deleteStmt);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
