package ch.hevs.androidproject644.js.workingtime.Controler;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

import ch.hevs.androidproject644.js.workingtime.DB.TaskDataSource;
import ch.hevs.androidproject644.js.workingtime.DB.WorkerDataSource;
import ch.hevs.androidproject644.js.workingtime.model.Task;
import ch.hevs.androidproject644.js.workingtime.model.Time;
import ch.hevs.androidproject644.js.workingtime.model.Worker;

/**
 * Created by Samuel on 18.11.2016.
 */

public class C_Task {

    public static Task getTasksById(long id, Context context){
        Task task;
        TaskDataSource tds = new TaskDataSource(context);
        task = tds.getTaskByID(id);
        return task;
    }

    public static long getTotalDurationByTask(List<Time> timeList){
        long duration = 0;
        for (Time t : timeList )
        {
            duration+=t.get_duration();
        }
        return duration;
    }
}
