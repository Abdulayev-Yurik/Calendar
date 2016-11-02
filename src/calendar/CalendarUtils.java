package calendar;

import java.util.Arrays;
import java.util.List;

import static calendar.PrintToConsole.ANSI_RED;
import static calendar.PrintToConsole.ANSI_RESET;

/**
 * Created by employee on 11/2/16.
 */
public class CalendarUtils {

    public static boolean isWeekend(int dayOfWeek, List weekends){
        return weekends.contains(dayOfWeek);
    }

    public static String toWeekendColor(String value){
        return ANSI_RED + value + ANSI_RESET;
    }


}
