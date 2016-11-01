package ch.hevs.androidproject644.js.workingtime.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacques on 01.11.2016.
 */

public class ActivitiesManager {
    private static List<Activity> _activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity){
        _activities.add(activity);
    }

    public static List<Activity> getAllActivities(){
        return _activities;
    }

    public static void set_AllActivities(){

        _activities.add(new Activity(1,"Projet A",false));
        _activities.add(new Activity(2,"Projet B",true));
        _activities.add(new Activity(3,"Activité 3",false));
        _activities.add(new Activity(4,"Commande N°12",true));
    }
}
