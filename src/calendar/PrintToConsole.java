package calendar;

import calendar.utils.CalendarUtils;
import calendar.utils.WeekDaysValues;
import calendar.utils.WeekdaysNames;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Created by yurik on 01.09.16.
 */
class PrintToConsole {

    private static final int MAX_WEEKS = 6;

    static void parseDate(int year, int month, int currentDay, List weekends, int firstDay) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(year, month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());

        System.out.println(generateView(monthLength, firstDayInWeek, currentDay, weekends, firstDay));
    }

    private static String generateView(int monthLength, int firstDayInWeek,
                                       int currentDay, List weekends, int customFirstDay) {
        StringBuilder builder = new StringBuilder();
        builder.append(new WeekdaysNames().getWeekNames(Locale.UK, weekends, customFirstDay)).append("\n");
        for (int numberWeek = 0; numberWeek < MAX_WEEKS; numberWeek++) {
            int v = CalendarUtils.backDay(customFirstDay - 1);                  // FIXME: 11/3/16 not correct
            builder.append(WeekDaysValues.getWeekDays(numberWeek, v, monthLength,
                    currentDay, Locale.UK, weekends))
                    .append("\n");
        }
        return builder.toString();
    }
}