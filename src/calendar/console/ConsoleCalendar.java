package calendar.console;

import calendar.interfaces.Calendar;
import calendar.utils.CalendarUtils;
import calendar.utils.WeekdaysValues;
import calendar.utils.WeekdaysName;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public class ConsoleCalendar implements Calendar {

    @Override
    public void print(int startWithCustomDay, LocalDate currentDate, List<Integer> weekends) {
        parseDate(startWithCustomDay, currentDate, weekends);
    }

    private void parseDate(int startWithCustomDay, LocalDate date, List<Integer> weekends) {
        LocalDate thisDate = LocalDate.of(date.getYear(),
                date.getMonth().getValue(), 1);

        int firstDayOfWeek = thisDate.getDayOfWeek().getValue();

        System.out.println(generateView(firstDayOfWeek, thisDate,
                date.getDayOfMonth(), startWithCustomDay, weekends));
    }

    private String generateView(int firstDayOfWeek, LocalDate thisDate, int currentDay,
                                int startWeekendOfDay, List<Integer> weekends) {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames(startWeekendOfDay, weekends));
        view.append("\n");
        view.append(getMonthValues(thisDate, firstDayOfWeek, startWeekendOfDay, currentDay, weekends));
        return view.toString();
    }

    private String getMonthValues(LocalDate thisDate,
                                  int firstDayOfWeek, int startWeekendOfDay, int currentDay, List<Integer> weekends) {
        DayOfWeek dayOfWeek = thisDate.getDayOfWeek();
        StringBuilder days = new StringBuilder();
        int before = CalendarUtils.getBeforeValue(thisDate, startWeekendOfDay);
        for (int i = before; i > 0; i--) {
            String formattedDay = WeekdaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(thisDate.minusDays(i).getDayOfMonth() + ""));
            days.append(CalendarUtils.toAnotherMonthConsoleColor(formattedDay));
        }
        for (int numberDay = 1; numberDay <= thisDate.getMonth().length(thisDate.isLeapYear()); numberDay++) {
            String formattedDay = CalendarUtils.getFormattedDay(numberDay + "");
            if (numberDay == currentDay) {
                days.append(CalendarUtils.toThisDayConsoleColor(formattedDay));
            }else if (CalendarUtils.isWeekend(dayOfWeek.getValue(), weekends)){
                days.append(CalendarUtils.toWeekendConsoleColor(formattedDay));
            }else {
                days.append(formattedDay);
            }
            if (dayOfWeek.getValue() == CalendarUtils.backDay(startWeekendOfDay)){
                days.append("\n");
            }
            dayOfWeek = dayOfWeek.plus(1);
        }
        for (int day = 1; dayOfWeek.getValue() != DayOfWeek.of(startWeekendOfDay).getValue();
             dayOfWeek = dayOfWeek.plus(1) , day++){
            String formattedDay = WeekdaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(day + ""));
            days.append(CalendarUtils.toAnotherMonthConsoleColor(formattedDay));
        }
        return days.toString();
    }

    private String getWeekNames(int startWeekendOfDay, List<Integer> weekends) {
        StringBuilder builder = new StringBuilder();
        for (DayOfWeek dayOfWeek : WeekdaysName.getWeekdays(startWeekendOfDay)) {
            String dayName = CalendarUtils.getFormattedDay(WeekdaysName.getDayValue(dayOfWeek));
            builder.append(CalendarUtils.isWeekend(dayOfWeek.getValue(), weekends) ?
                    CalendarUtils.toWeekendConsoleColor(dayName) : dayName);
        }
        return builder.toString();
    }
}
