package sample.java.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.java.model.Challenge;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChallengesDao {

    //Бүх challenge - ыг өгөгдлийн сангаас дуудах
    public static ObservableList<Challenge> getChallengesList() throws SQLException, ClassNotFoundException {

        String selectStmt = "SELECT * FROM challenges";

        ResultSet rs = DbConnection.dbExecuteQuery(selectStmt);
        ObservableList<Challenge> challengesList = FXCollections.observableArrayList();

        while(rs.next()){
            Challenge challenge = new Challenge(
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("started_date").toLocalDate(),
                    rs.getInt("duration"),
                    rs.getString("type"),
                    rs.getBoolean("completed")
            );
            challengesList.add(challenge);
        }
        return challengesList;
    }
    //Шинэ challenge өгөдлийн сан руу нэмэх
    public static void writeChallenge(Challenge challenge){
        String insertStmt =
                "INSERT INTO challenges\n" +
                        "VALUES('"+challenge.getTitle()+"','"+challenge.getDescription()+"','"+challenge.getType()+"','"+challenge.getStartedDate()+"','"+challenge.getDuration()+"','"+(challenge.isCompleted() ? 1 : 0)+"\n')";
        try{
            DbConnection.dbExecuteUpdate(insertStmt);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    //Биелэгдсэн challenge -ыг тэмдэглэх
    public static void updateCompletedCol(Challenge challenge){
        String updateCompletedCol =
                "UPDATE challenges\n" +
                        "SET completed = 1\n" +
                        "WHERE title = '" + challenge.getTitle() + "'\n";

        try{
            DbConnection.dbExecuteUpdate(updateCompletedCol);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //Challenge устгах
    public static void deleteChallenge(Challenge challenge){
        String deleteStmt =
                "DELETE FROM challenges\n" +
                        "WHERE title = '"+ challenge.getTitle()+"'\n";

        try{
            DbConnection.dbExecuteUpdate(deleteStmt);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
