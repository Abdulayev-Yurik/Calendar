package calendar;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

/**
 * Created by yurik on 01.09.16.
 */
public class PrintToConsole {

    static final String TYPICAL_STRING_FORMAT = "%4s";
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_RED = "\u001B[31m";
    static final String ANSI_CYAN = "\u001B[36m";
    static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS = 6;

    public static void parseDate(int year, int month, int day) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(year, month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
        System.out.println(generateView(monthLength, firstDayInWeek, day));
    }

    private static String generateView(int monthLength, int firstDayInWeek, int day) {
        StringBuilder builder = new StringBuilder();
        builder.append(new WeekNames().getWeekNames(Locale.UK, Arrays.asList(6, 7))).append("\n");
        for (int numberWeek = 0; numberWeek < MAX_WEEKS; numberWeek++) {
                builder.append(WeekDays.getWeekDays(numberWeek, 2, monthLength,
                    day, Locale.UK, Arrays.asList(6, 7)))
                    .append("\n");
        }
        return builder.toString();
    }
}