package calendar.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by employee on 11/3/16.
 */
public class WeekdaysValues extends CalendarUtils{

    public static String getWeekDays(int firstDayOfWeek,
                                     int monthLength) {
        getDays(monthLength);

        return null;
    }

    public static String[] getDays(int monthLength) {
        String[] days = new String[monthLength];
        for (int day = 1; day <= days.length; day++) {
            days[day - 1] = day + "";
        }
        return days;
    }

    private static boolean isThisMonth(int firstDayOfWeek, int monthLength, int day) {
        return day > firstDayOfWeek && day - firstDayOfWeek <= monthLength;
    }


    public static String getPreviousMonthDays(LocalDate thisDate, int startWeekendOfDay, String type) {
        StringBuilder days = new StringBuilder();
        for (int i = getBeforeValue(thisDate, startWeekendOfDay); i > 0; i--) {
            String formattedDay = WeekdaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(thisDate.minusDays(i).getDayOfMonth() + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    public static String getNextMonthDays(int startWithCustomDay, DayOfWeek dayOfWeek, String type) {
        StringBuilder builder = new StringBuilder();
        for (int day = 1; dayOfWeek.getValue() != DayOfWeek.of(startWithCustomDay).getValue();
             dayOfWeek = dayOfWeek.plus(1) , day++){
            String formattedDay = WeekdaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(day + ""));
            builder.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return builder.toString();
    }
}
