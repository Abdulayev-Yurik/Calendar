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

        System.out.println(generateView(thisDate,
                date.getDayOfMonth(), startWithCustomDay, weekends));
    }

    private String generateView(LocalDate thisDate, int currentDay,
                                int startWeekendOfDay, List<Integer> weekends) {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames(startWeekendOfDay, weekends));
        view.append("\n");
        view.append(getMonthValues(thisDate, startWeekendOfDay, currentDay, weekends));
        return view.toString();
    }

    private String getMonthValues(LocalDate thisDate, int startWeekendOfDay, int currentDay, List<Integer> weekends) {
        DayOfWeek dayOfWeek = thisDate.getDayOfWeek();
        return new StringBuilder()
                .append(WeekdaysValues.getPreviousMonthDays(thisDate, startWeekendOfDay, CalendarUtils.CONSOLE_VIEW))
                .append(WeekdaysValues.getMonthValues(thisDate, currentDay, dayOfWeek, weekends,
                        startWeekendOfDay, CalendarUtils.CONSOLE_VIEW))
                .append(WeekdaysValues.getNextMonthDays(startWeekendOfDay, CalendarUtils.CONSOLE_VIEW))
                .toString();
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
