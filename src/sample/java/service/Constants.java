package sample.java.service;

public class Constants {

    String todayPageColor = "#2B6B89";
    String taskPageColor = "#B73A52";
    String challengePageColor = "#635A6F";
    String dashBoardPageColor = "#384992";
    String weekSchedulePageColor = "#326B70";

    String[] dayOfWeek = {"MONDAY" , "TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
    String[] pageNames = {"todayBtn","taskBtn","challengesBtn","dashBoardBtn","weekScheduleBtn"} ;


    public String getWeekSchedulePageColor() { return weekSchedulePageColor; }

    public String getDashBoardPageColor() { return dashBoardPageColor; }

    public String getChallengePageColor() { return challengePageColor; }

    public String getTaskPageColor() { return taskPageColor; }

    public String[] getDayOfWeek() { return dayOfWeek; }

    public String[] getPageNames() { return pageNames; }

    public String getTodayPageColor() { return todayPageColor; }
}
