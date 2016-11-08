package calendar.console;

import calendar.interfaces.Calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public class ConsoleCalendar extends Calendar {
    private LocalDate thisDate;
    private DayOfWeek startWeek;
    private List<DayOfWeek> weekend;
    private LocalDate firstDayOfMonth;

    public ConsoleCalendar() {
        this(LocalDate.now());
    }

    public ConsoleCalendar(LocalDate thisDate) {
        this(thisDate, DayOfWeek.MONDAY);
    }

    public ConsoleCalendar(LocalDate thisDate, DayOfWeek startWeek) {
        this(thisDate, startWeek, Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    }

    public ConsoleCalendar(LocalDate thisDate, DayOfWeek startWeek, List<DayOfWeek> weekend) {
        this.thisDate = thisDate;
        this.startWeek = startWeek;
        this.weekend = weekend;
    }

    public void print() {
        firstDayOfMonth = LocalDate.of(thisDate.getYear(),
                thisDate.getMonth().getValue(), 1);

        System.out.println(generateView());
    }

    public String generateView() {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames());
        view.append("\n");
        view.append(getMonthValues());
        return view.toString();
    }

    public String getMonthValues() {
        return new StringBuilder()
                .append(getPreviousMonthDays(firstDayOfMonth, startWeek.getValue(), CONSOLE_VIEW))
                .append(getCurrentMonthValues(firstDayOfMonth, thisDate.getDayOfMonth(),
                        startWeek.getValue(), weekend, CONSOLE_VIEW))
                .append(getNextMonthDays(startWeek.getValue(), CONSOLE_VIEW))
                .toString();
    }

    public String getWeekNames() {
        StringBuilder builder = new StringBuilder();
        for (DayOfWeek dayOfWeek : getWeekdays(startWeek.getValue())) {
            String dayName = getFormattedDay(getDayValue(dayOfWeek));
            builder.append(isWeekend(dayOfWeek, weekend) ?
                    toWeekendColor(dayName, CONSOLE_VIEW) : dayName);
        }
        return builder.toString();
    }
}
