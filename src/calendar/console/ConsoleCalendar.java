package calendar.console;

import calendar.interfacec.Calendar;
import calendar.utils.CalendarUtils;
import calendar.utils.WeekDaysValues;
import calendar.utils.WeekdaysName;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public class ConsoleCalendar implements Calendar {

    @Override
    public void print(int startWithCustomDay, List<Integer> weekends) {
        parseDate(startWithCustomDay, weekends);
    }

    private void parseDate(int startWithCustomDay, List<Integer> weekends) {
        LocalDate currentDate = LocalDate.now();
        LocalDate thisDate = LocalDate.of(currentDate.getYear(),
                currentDate.getMonth().getValue(), 1);

        int firstDayOfWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());

        System.out.println(generateView(firstDayOfWeek, thisDate,
                currentDate.getDayOfMonth(), monthLength, startWithCustomDay, weekends));
    }

    private String generateView(int firstDayOfWeek, LocalDate thisDate, int currentDay,
                                int monthLength, int startWeekendOfDay, List<Integer> weekends) {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames(startWeekendOfDay, weekends));
        view.append("\n");
        view.append(getMonthValues(monthLength, thisDate, firstDayOfWeek, startWeekendOfDay, currentDay, weekends));
        return view.toString();
    }

    private String getMonthValues(int length, LocalDate thisDate,
                                  int firstDayOfWeek, int startWeekendOfDay, int currentDay, List<Integer> weekends) {
        DayOfWeek dayOfWeek = thisDate.getDayOfWeek();
        StringBuilder days = new StringBuilder();
//        int divider = thisDate.getDayOfWeek().
//        String[] days = WeekDaysValues.getDays(monthLength);
        int before = getValue(firstDayOfWeek, startWeekendOfDay);
        for (int i = before; i > 0; i--) {
            String formattedDay = WeekDaysValues.getFormattedDay(
                    CalendarUtils.getFormattedDay(thisDate.minusDays(i).getDayOfMonth() + ""));
            days.append(CalendarUtils.toAnotherMonthColor(formattedDay));
        }
        for (int numberDay = 1; numberDay <= thisDate.getMonth().length(thisDate.isLeapYear()); numberDay++) {
            String formattedDay = CalendarUtils.getFormattedDay(numberDay + "");
            if (numberDay == currentDay) {
                days.append(CalendarUtils.toThisDayColor(formattedDay));
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

        return days.toString();
    }

    private int getValue(int firstDay, int customFirstDay){
        if (firstDay ==  customFirstDay) return 0;
        return firstDay > customFirstDay ? firstDay - 1 : 7 - (customFirstDay - firstDay);
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
