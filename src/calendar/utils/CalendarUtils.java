package calendar.utils;

import java.util.List;

/**
 * Created by employee on 11/2/16.
 */
public class CalendarUtils {

    static final String TYPICAL_STRING_FORMAT = "%4s";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    static final int DAYS_IN_WEEK = 7;
    protected static final int MAX_WEEKS = 6;


    static boolean isWeekend(int dayOfWeek, List weekends){
        return weekends.contains(dayOfWeek);
    }

    static String toWeekendColor(String value){
        return ANSI_RED + value + ANSI_RESET;
    }

    static String toThisDayColor(String value){
        return ANSI_CYAN + value + ANSI_RESET;
    }

    static int nextDay(int value) {
        return value == 7 ? 1 : ++value;
    }

    public static int backDay(int value) {
        int[] a = {2,1,7,6,5,4,3};
        return a[value];
    }
}
