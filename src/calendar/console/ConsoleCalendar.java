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
    private LocalDate firstDayOfMonth;

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
    public void print() {
        firstDayOfMonth = LocalDate.of(thisDate.getYear(),
                thisDate.getMonth().getValue(), 1);

        System.out.println(generateView(thisDate.getDayOfMonth()));
    }

    private String generateView(int currentDay) {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames());
        view.append("\n");
        view.append(getMonthValues(currentDay));
        return view.toString();
    }

    private String getMonthValues(int currentDay) {
        DayOfWeek dayOfWeek = firstDayOfMonth.getDayOfWeek();
        return new StringBuilder()
                .append(WeekdaysValues.getPreviousMonthDays(firstDayOfMonth, startWeek.getValue(), CalendarUtils.CONSOLE_VIEW))
                .append(WeekdaysValues.getMonthValues(firstDayOfMonth, currentDay, dayOfWeek, weekend,
                        startWeek.getValue(), CalendarUtils.CONSOLE_VIEW))
                .append(WeekdaysValues.getNextMonthDays(startWeek.getValue(), CalendarUtils.CONSOLE_VIEW))
                .toString();
    }

    private String getWeekNames() {
        StringBuilder builder = new StringBuilder();
        for (DayOfWeek dayOfWeek : WeekdaysName.getWeekdays(startWeek.getValue())) {
            String dayName = CalendarUtils.getFormattedDay(WeekdaysName.getDayValue(dayOfWeek));
            builder.append(CalendarUtils.isWeekend(dayOfWeek, weekend) ?
                    CalendarUtils.toWeekendColor(dayName, CalendarUtils.CONSOLE_VIEW) : dayName);
        }
        return builder.toString();
    }
}
