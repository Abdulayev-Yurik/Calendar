package calendar.interfaces;

import calendar.utils.CalendarUtils;
import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by employee on 11/8/16.
 */
public abstract class Calendar extends CalendarUtils {

    private DayOfWeek endDayOfCurrentMonth;

    public abstract void print();

    public abstract String generateView();

    public abstract String getMonthValues();

    public abstract String getWeekNames();

    protected static String getPreviousMonthDays(LocalDate firstDayOfMonth, int startWeekendOfDay, String type) {
        StringBuilder days = new StringBuilder();
        for (int i = getBeforeValue(firstDayOfMonth, startWeekendOfDay); i > 0; i--) {
            String formattedDay = getFormattedDay(
                    CalendarUtils.getFormattedDay(firstDayOfMonth.minusDays(i).getDayOfMonth() + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    protected String getNextMonthDays(int startWithCustomDay, String type) {
        StringBuilder days = new StringBuilder();
        for (int day = 1; endDayOfCurrentMonth.getValue() != DayOfWeek.of(startWithCustomDay).getValue();
             endDayOfCurrentMonth = endDayOfCurrentMonth.plus(1) , day++){
            String formattedDay = getFormattedDay(
                    CalendarUtils.getFormattedDay(day + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    protected String getCurrentMonthValues(LocalDate thisDate, int currentDay, int startWeekendOfDay,
                                           List<DayOfWeek> weekend, String typeView) {
        StringBuilder days = new StringBuilder();
        for (int numberDay = 1; numberDay <= thisDate.getMonth().length(thisDate.isLeapYear()); numberDay++) {
            days.append(getColorDay(numberDay, currentDay, thisDate.getDayOfWeek(), weekend, typeView));
            if (thisDate.getDayOfWeek().getValue() == CalendarUtils.backDay(startWeekendOfDay)) {
                days.append(toNewLine(typeView));
            }
            thisDate = thisDate.plusDays(1);
        }
        endDayOfCurrentMonth = thisDate.getDayOfWeek();
        return days.toString();
    }

    private static String toNewLine(String type) {
        if (type.equals(WEB_VIEW))
            return "\n</tr>\n<tr>\n";
        else if (type.equals(CONSOLE_VIEW))
            return "\n";
        return "";
    }

    private String getColorDay(int numberDay, int currentDay,
                                      DayOfWeek dayOfWeek, List<DayOfWeek> weekends, String typeView) {
        String formattedDay = CalendarUtils.getFormattedDay(numberDay + "");
        if (isCurrentDay(numberDay, currentDay)) {
            return toThisDayColor(formattedDay, typeView);
        } else if (isWeekend(dayOfWeek, weekends)) {
            return toWeekendColor(formattedDay, typeView);
        } else {
            return getDay(formattedDay, typeView);
        }
    }

    private boolean isCurrentDay(int numberDay, int currentDay) {
        return numberDay == currentDay;
    }

    private String getDay(String value, String type) {
        if (type.equals(WEB_VIEW))
            return "<td>" + value + "</td>";
        else
            return value;
    }

    protected List<DayOfWeek> getWeekdays(int startDayIndex) {
        List<DayOfWeek> names = new ArrayList<>();
        DayOfWeek dayOfWeek = DayOfWeek.of(startDayIndex);
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            names.add(dayOfWeek);
            dayOfWeek = dayOfWeek.plus(1);
        }
        return names;
    }

    @NotNull
    protected static String getDayValue(DayOfWeek dayOfWeek) {
        return dayOfWeek
                .getDisplayName(TextStyle.SHORT, Locale.GERMANY)
                .toUpperCase();
    }
}
