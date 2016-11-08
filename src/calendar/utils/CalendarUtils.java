package calendar.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by employee on 11/2/16.
 */
public class CalendarUtils {

    static final String TYPICAL_STRING_FORMAT = "%4s";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String WEB_VIEW = "web";
    public static final String CONSOLE_VIEW = "console";
    static final int DAYS_IN_WEEK = 7;

    public static boolean isWeekend(DayOfWeek dayOfWeek, List<DayOfWeek> weekends){
        return weekends.contains(dayOfWeek);
    }

    protected static String toAnotherMonthColor(String value, String type){
        if (type.equals(WEB_VIEW))
            return "<td class=\"anotherMonthColor\">" + value + "</td>";
        else if (type.equals(CONSOLE_VIEW))
            return ANSI_YELLOW + value + ANSI_RESET;
        else
            return value;
    }

    protected static String toThisDayColor(String value, String type) {
        if (type.equals(WEB_VIEW))
            return "<td class=\"currentDay\">" + value + "</td>";
        else if (type.equals(CONSOLE_VIEW))
            return ANSI_CYAN + value + ANSI_RESET;
        return value;
    }

    public static String toWeekendColor(String value, String type) {
        if (type.equals(WEB_VIEW))
            return "<td class=\"weekend\">" + value + "</td>";
        else if (type.equals(CONSOLE_VIEW))
            return ANSI_RED + value + ANSI_RESET;
        return value;
    }

    public static int backDay(int value) {
        return value == 1 ? 7 : --value;
    }

    public static String getFormattedDay(String numberDay) {
        return String.format(CalendarUtils.TYPICAL_STRING_FORMAT, numberDay);
    }

    public static int getBeforeValue(LocalDate localDate, int customFirstDay) {
        return localDate.getDayOfWeek().getValue() == customFirstDay ?
                7 - localDate.minusDays(1).getDayOfMonth() : getBeforeValue(localDate.plusDays(1), customFirstDay);
    }

}
