package calendar.console;

import calendar.interfaces.Calendar;
import calendar.utils.CalendarUtils;
import calendar.utils.WeekdaysName;
import calendar.utils.WeekdaysValues;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public class ConsoleCalendar implements Calendar {

    private LocalDate thisDate;
    private DayOfWeek startWeek;
    private List<DayOfWeek> weekend;

    public ConsoleCalendar() {
        this(LocalDate.now());
    }

    public ConsoleCalendar(LocalDate thisDate) {
        this(thisDate, DayOfWeek.MONDAY);
    }

    public ConsoleCalendar(LocalDate thisDate, DayOfWeek startWeek){
        this(thisDate, startWeek, Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    }

    public ConsoleCalendar(LocalDate thisDate, DayOfWeek startWeek, List<DayOfWeek> weekend) {
        this.thisDate = thisDate;
        this.startWeek = startWeek;
        this.weekend = weekend;
    }

    @Override
    public void print(LocalDate currentDate) {
        parseDate(currentDate);
    }

    private void parseDate(LocalDate date) {
        thisDate = LocalDate.of(date.getYear(),
                date.getMonth().getValue(), 1);

        System.out.println(generateView(date.getDayOfMonth()));
    }

    private String generateView(int currentDay) {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames());
        view.append("\n");
        view.append(getMonthValues(currentDay));
        return view.toString();
    }

    private String getMonthValues(int currentDay) {
        DayOfWeek dayOfWeek = thisDate.getDayOfWeek();
        return new StringBuilder()
                .append(WeekdaysValues.getPreviousMonthDays(thisDate, startWeek, CalendarUtils.CONSOLE_VIEW))
                .append(WeekdaysValues.getMonthValues(thisDate, currentDay, dayOfWeek, weekend,
                        startWeek, CalendarUtils.CONSOLE_VIEW))
                .append(WeekdaysValues.getNextMonthDays(startWeek, CalendarUtils.CONSOLE_VIEW))
                .toString();
    }

    private String getWeekNames() {
        StringBuilder builder = new StringBuilder();
        for (DayOfWeek dayOfWeek : WeekdaysName.getWeekdays(startWeek.getValue())) {
            String dayName = CalendarUtils.getFormattedDay(WeekdaysName.getDayValue(dayOfWeek));
            builder.append(CalendarUtils.isWeekend(dayOfWeek.getValue(), weekend) ?
                    CalendarUtils.toWeekendColor(dayName, CalendarUtils.CONSOLE_VIEW) : dayName);
        }
        return builder.toString();
    }
}
