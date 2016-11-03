package calendar;

import java.util.Arrays;
import java.util.List;

import static calendar.PrintToConsole.ANSI_CYAN;
import static calendar.PrintToConsole.ANSI_RED;
import static calendar.PrintToConsole.ANSI_RESET;

/**
 * Created by employee on 11/2/16.
 */
public class CalendarUtils {

    static final String TYPICAL_STRING_FORMAT = "%4s";


    public static boolean isWeekend(int dayOfWeek, List weekends){
        return weekends.contains(dayOfWeek);
    }

    public static String toWeekendColor(String value){
        return ANSI_RED + value + ANSI_RESET;
    }

    public static String toThisDayColor(String value){
        return ANSI_CYAN + value + ANSI_RESET;
    }


}
