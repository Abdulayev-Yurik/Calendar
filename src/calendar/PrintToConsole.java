package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.String.format;

/**
 * Created by yurik on 01.09.16.
 */
public class PrintToConsole {

    static final String TYPICAL_STRING_FORMAT = "%4s";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS = 6;
    private static int counter = 1;

    public static void parseDate(int year, int month, int day) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(year, month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
        generateView(monthLength, firstDayInWeek, day);
    }

    private static void generateView(int monthLength, int firstDayInWeek, int day) {
        System.out.println(new WeekNames().getWeekNames(Locale.UK, Arrays.asList(0, 6, 4)));
        for (int numberWeek = 0; numberWeek < MAX_WEEKS; numberWeek++) {
//            WeekFields.of(Locale.ENGLISH).getMinimalDaysInFirstWeek()
//            getLine(numberWeek,firstDayInWeek, day, monthLength);
        }
//        for (int week = 1; week <= MAX_WEEKS; week++) {
//            for (int dayInWeek = 1; dayInWeek <= DAYS_IN_WEEK; dayInWeek++) {
//                if (counter <= monthLength)
//                    System.out.print(printDay(firstDayInWeek, dayInWeek, week, day));
//                else
//                    return;
//            }
//            System.out.print("\n");
//        }
    }

    private static String printDay(int firstDayInWeek, int dayInWeek, int week, int day) {
        String dayValue;
        if (week == 1 && dayInWeek < firstDayInWeek)
            dayValue = getDayFormat("");
        else {
            dayValue = getTypeDay(week, dayInWeek, day, Integer.toString(counter));
            counter++;
        }
        return dayValue;
    }

    private static String getTypeDay(int week, int dayInWeek, int day, String value) {
        if (counter == day && week != 0)
            return ANSI_CYAN + getDayFormat(value) + ANSI_RESET;
        else if (dayInWeek == DayOfWeek.SATURDAY.getValue() || dayInWeek == DayOfWeek.SUNDAY.getValue())
            return ANSI_RED + getDayFormat(value) + ANSI_RESET;
        else
            return getDayFormat(value);
    }

    private static String getDayFormat(String value) {
        return format(TYPICAL_STRING_FORMAT, value);
    }

    private static String getDaysName(int dayNumber) {
        return DayOfWeek.of(dayNumber).getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();
    }
}