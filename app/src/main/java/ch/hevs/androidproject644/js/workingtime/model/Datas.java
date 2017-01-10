package ch.hevs.androidproject644.js.workingtime.model;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Jacques on 31.10.2016.
 */

public class Datas {

    public final static String[] formatDate = {"dd/MM/yyyy","MM.dd.yyyy"};
    private final static Locale[] formatDateLocal = { Locale.FRANCE, Locale.US};
    public static int dateFormatIndex = 0 ;

    public final static String MODE = "ch.hevs.androidprojerct644.js.workingtime.MODE";
    public final static String EDIT = "EDIT";
    public final static String NEW = "NEW";
    public final static String VIEW = "VIEW";

    //public final static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(formatDate[1],Locale.US);

    public final static SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss");

    public static SimpleDateFormat formatDate(){
        return new SimpleDateFormat(formatDate[dateFormatIndex],formatDateLocal[dateFormatIndex]);
    }

   /* public static void setDateFormatIndex(int dateFormatIndex) {
        Datas.dateFormatIndex = dateFormatIndex;
    }*/
}
