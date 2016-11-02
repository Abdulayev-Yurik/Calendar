package test;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.lang.String.format;

/**
 * Created by employee on 11/2/16.
 */
public class TestedMethods {
    protected static final String TYPICAL_STRING_FORMAT = "%4s";
    protected static final String ANSI_RESET = "\u001B[0m";
    protected static final String ANSI_RED = "\u001B[31m";
    protected static final String ANSI_CYAN = "\u001B[36m";
    protected static final int DAYS_IN_WEEK = 7;
    protected static final int MAX_WEEKS = 6;
    protected static int counter = 1;

    static String getDayName(int dayNumber, Locale locale) {
        return DayOfWeek.of(dayNumber).getDisplayName(TextStyle.SHORT, locale).toUpperCase();
    }

    static String getTypeDay(int week, int dayInWeek, int day, String value) {
        if (counter == day && week != 0)
            return ANSI_CYAN + getDayFormat(value) + ANSI_RESET;
        else if (dayInWeek == DayOfWeek.SATURDAY.getValue() || dayInWeek == DayOfWeek.SUNDAY.getValue())
            return ANSI_RED + getDayFormat(value) + ANSI_RESET;
        else
            return getDayFormat(value);
    }

    static String getDayFormat(String value) {
        return format(TYPICAL_STRING_FORMAT, value);
    }

    static String getDayName(int dayNumber) {
        return DayOfWeek.of(dayNumber).getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase();
    }

    static String printDay(int firstDayInWeek, int dayInWeek, int week, int day) {
        String dayValue;
        if (week == 1 && dayInWeek < firstDayInWeek)
            dayValue = getDayFormat("");
        else {
            dayValue = getTypeDay(week, dayInWeek, day, Integer.toString(counter));
            counter++;
        }
        return dayValue;
    }

}
