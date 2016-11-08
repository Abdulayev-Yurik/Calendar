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

    protected LocalDate thisDate;
    protected DayOfWeek startWeek;
    protected List<DayOfWeek> weekend;
    protected LocalDate firstDayOfMonth;

    public void innit(LocalDate thisDate, DayOfWeek startWeek, List<DayOfWeek> weekend) {
        this.thisDate = thisDate;
        this.startWeek = startWeek;
        this.weekend = weekend;

        firstDayOfMonth = LocalDate.of(thisDate.getYear(),
                thisDate.getMonth().getValue(), 1);
    }

    private DayOfWeek endDayOfCurrentMonth;

    public abstract void print();

    public abstract void generateView();

    public abstract String getMonthValues();

    public abstract String getWeekNames();

    protected String getPreviousMonthDays(String type) {
        StringBuilder days = new StringBuilder();
        for (int i = getBeforeValue(firstDayOfMonth, startWeek.getValue()); i > 0; i--) {
            String formattedDay = getFormattedDay(
                    CalendarUtils.getFormattedDay(firstDayOfMonth.minusDays(i).getDayOfMonth() + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    protected String getNextMonthDays(String type) {
        StringBuilder days = new StringBuilder();
        for (int day = 1; endDayOfCurrentMonth.getValue() != startWeek.getValue();
             endDayOfCurrentMonth = endDayOfCurrentMonth.plus(1) , day++){
            String formattedDay = getFormattedDay(
                    CalendarUtils.getFormattedDay(day + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay, type));
        }
        return days.toString();
    }


    protected String getCurrentMonthValues(String typeView) {
        StringBuilder days = new StringBuilder();
        for (int numberDay = 1; numberDay <= firstDayOfMonth.getMonth().length(firstDayOfMonth.isLeapYear()); numberDay++) {
            days.append(getColorDay(numberDay, thisDate.getDayOfMonth(), firstDayOfMonth.getDayOfWeek(), typeView));
            if (firstDayOfMonth.getDayOfWeek().getValue() == CalendarUtils.backDay(startWeek.getValue())) {
                days.append(toNewLine(typeView));
            }
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }
        endDayOfCurrentMonth = firstDayOfMonth.getDayOfWeek();
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
                                      DayOfWeek dayOfWeek, String typeView) {
        String formattedDay = CalendarUtils.getFormattedDay(numberDay + "");
        if (isCurrentDay(numberDay, currentDay)) {
            return toThisDayColor(formattedDay, typeView);
        } else if (isWeekend(dayOfWeek)) {
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
                .getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                .toUpperCase();
    }

    protected boolean isWeekend(DayOfWeek dayOfWeek){
        return weekend.contains(dayOfWeek);
    }
}
