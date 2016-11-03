package ch.hevs.androidproject644.js.workingtime.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.androidproject644.js.workingtime.model.Worker;

/**
 * Created by Jacques on 01.11.2016.
 */

public class WorkersManager {

    private static List<Worker> _workers = new ArrayList<Worker>();
    public static List<Worker> getAllWorker(){
        return _workers;
    }
    public static void addWorker(Worker worker){ _workers.add(worker);}

    public static void set_AllWorkers(){
        _workers.add(new Worker(1,"Duchmol","Florent", Date.valueOf("1980-07-07"),'m',false));
        _workers.add(new Worker(2,"Schaler","Noémie", Date.valueOf("1991-12-12"),'f',true));
        _workers.add(new Worker(3,"Dupont","Thierry", Date.valueOf("1971-10-09"),'m',false));
        _workers.add(new Worker(4,"Fournier","Pierre", Date.valueOf("1996-01-08"),'m',true));
        _workers.add(new Worker(5,"Fournier","Pierre", Date.valueOf("1948-12-18"),'m',true));
        _workers.add(new Worker(6,"Fournier","Clémentine", Date.valueOf("1996-01-08"),'f',true));
        _workers.add(new Worker(7,"Nanchen","Xavier", Date.valueOf("1969-10-27"),'m',true));
        _workers.add(new Worker(8,"Coppey","Samuel", Date.valueOf("1991-05-18"),'m',true));
        _workers.add(new Worker(9,"Pannatier","Alphonse", Date.valueOf("1990-10-18"),'m',false));
        _workers.add(new Worker(10,"Herren","Jacques", Date.valueOf("1975-12-29"),'m',true));
        _workers.add(new Worker(11,"Herren","Véronique", Date.valueOf("1981-01-08"),'f',true));
        _workers.add(new Worker(12,"Nobs","Olivia", Date.valueOf("1986-05-05"),'f',true));
    }
}
