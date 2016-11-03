package ch.hevs.androidproject644.js.workingtime.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacques on 01.11.2016.
 */

public class TasksManager {
    private static List<Task> _tasks = new ArrayList<Task>();
    public static List<Task> getAllTasks(){
        return _tasks;
    }
    public static void addTask(Task task){ _tasks.add(task);}

    public static void set_AllTask(){
        Company c = new Company(8,"Test S.A.",true);
        Activity a = new Activity(12, "Atelier", true);
        Worker w = new Worker(12,"Nobs","Olivia", Date.valueOf("1986-05-05"),'f',true);

        _tasks.add(new Task(1, Date.valueOf("2016-10-27"),495,"Commentaire libre de la tâches",w,c,a));
        _tasks.add(new Task(2, Date.valueOf("2016-10-28"),210,"Commentaire libre de la tâches",w,c,a));
        _tasks.add(new Task(3, Date.valueOf("2016-10-29"),357,"Commentaire libre de la tâches",w,c,a));
        _tasks.add(new Task(4, Date.valueOf("2016-10-30"),498,"Commentaire libre de la tâches",w,c,a));
    }
}
