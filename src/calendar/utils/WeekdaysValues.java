package calendar.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by employee on 11/3/16.
 */
public class WeekdaysValues extends CalendarUtils{

    private static DayOfWeek endDayOfCurrentMonth;

    public static String getPreviousMonthDays(LocalDate firstDayOfMonth, int startWeekendOfDay, String type) {
        StringBuilder days = new StringBuilder();
        for (int i = getBeforeValue(firstDayOfMonth, startWeekendOfDay); i > 0; i--) {
            String formattedDay = WeekdaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(firstDayOfMonth.minusDays(i).getDayOfMonth() + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    public static String getNextMonthDays(int startWithCustomDay, String type) {
        StringBuilder days = new StringBuilder();
        for (int day = 1; endDayOfCurrentMonth.getValue() != DayOfWeek.of(startWithCustomDay).getValue();
             endDayOfCurrentMonth = endDayOfCurrentMonth.plus(1) , day++){
            String formattedDay = WeekdaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(day + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    public static String getMonthValues(LocalDate thisDate, int currentDay, DayOfWeek dayOfWeek,
                                        List<DayOfWeek> weekends, int startWeekendOfDay, String typeView) {
        StringBuilder days = new StringBuilder();
        for (int numberDay = 1; numberDay <= thisDate.getMonth().length(thisDate.isLeapYear()); numberDay++) {
            String formattedDay = CalendarUtils.getFormattedDay(numberDay + "");
            days.append(getColorDay(numberDay, currentDay, formattedDay, dayOfWeek, weekends, typeView));
            if (dayOfWeek.getValue() == CalendarUtils.backDay(startWeekendOfDay)) {
                days.append(toNewLine(typeView));
            }
            dayOfWeek = dayOfWeek.plus(1);
        }
        endDayOfCurrentMonth = dayOfWeek;
        return days.toString();
    }

    private static String toNewLine(String type) {
        if (type.equals(WEB_VIEW))
            return "\n</tr>\n<tr>\n";
        else if (type.equals(CONSOLE_VIEW))
            return "\n";
        return "";
    }

    private static String getColorDay(int numberDay, int currentDay, String formattedDay,
                                      DayOfWeek dayOfWeek, List<DayOfWeek> weekends, String typeView) {
        if (isCurrentDay(numberDay, currentDay)) {
            return toThisDayColor(formattedDay, typeView);
        } else if (isWeekend(dayOfWeek, weekends)) {
            return toWeekendColor(formattedDay, typeView);
        } else {
            return getDay(formattedDay, typeView);
        }
    }

    static boolean isCurrentDay(int numberDay, int currentDay) {
        return numberDay == currentDay;
    }

    private static String getDay(String value, String type) {
        if (type.equals(WEB_VIEW))
            return "<td>" + value + "</td>";
        else
            return value;
    }
}
