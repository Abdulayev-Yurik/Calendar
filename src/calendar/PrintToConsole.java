package calendar;

import calendar.utils.CalendarUtils;
import calendar.utils.WeekDays;
import calendar.utils.WeekNames;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Created by yurik on 01.09.16.
 */
public class PrintToConsole {

    private static final int MAX_WEEKS = 6;

    public static void parseDate(int year, int month, int day, List weekends, int firstDay) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(year, month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
        System.out.println(generateView(monthLength, firstDayInWeek, day, weekends, firstDay));
    }

    private static String generateView(int monthLength, int firstDayInWeek,
                                       int day, List weekends, int customFirstDay) {
        StringBuilder builder = new StringBuilder();
        builder.append(new WeekNames().getWeekNames(Locale.UK, weekends, customFirstDay)).append("\n");
        for (int numberWeek = 0; numberWeek < MAX_WEEKS; numberWeek++) {
            int v = CalendarUtils.backDay(firstDayInWeek);
            builder.append(WeekDays.getWeekDays(numberWeek, 2, monthLength,
                    day, Locale.UK, weekends))
                    .append("\n");
        }
        return builder.toString();
    }
}