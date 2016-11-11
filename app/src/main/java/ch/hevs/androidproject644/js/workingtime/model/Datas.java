package ch.hevs.androidproject644.js.workingtime.model;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Jacques on 31.10.2016.
 */

public class Datas {

    public final static String MODE = "ch.hevs.androidprojerct644.js.workingtime.MODE";
    public final static String EDIT = "EDIT";
    public final static String NEW = "NEW";
    public final static String VIEW = "VIEW";

    public final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
    public final static SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");

}
