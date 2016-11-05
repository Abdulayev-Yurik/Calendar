package calendar.console;

import calendar.interfacec.Calendar;
import calendar.utils.CalendarUtils;
import calendar.utils.WeekdaysName;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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

        System.out.println(generateView(firstDayOfWeek,
                currentDate.getDayOfMonth(), monthLength, startWithCustomDay, weekends));
    }

    private String generateView(int firstDayOfWeek, int currentDay,
                                int monthLength, int startWeekendOfDay, List<Integer> weekends) {
        StringBuilder view = new StringBuilder();
        view.append(getWeekNames(startWeekendOfDay, weekends));
        view.append(getMonthValues());
        return view.toString();
    }

    private String getMonthValues() {


        return null;
    }

    private String getWeekNames(int startWeekendOfDay, List<Integer> weekends) {
        StringBuilder builder = new StringBuilder();
        List<String> dayNames = WeekdaysName.getWeekdaysName(Locale.UK, startWeekendOfDay);
        for (int day = 1; day <= dayNames.size(); day++) {
            String dayName = dayNames.get(day - 1);
            builder.append(CalendarUtils.isWeekend(day, weekends) ?
                    CalendarUtils.toWeekendConsoleColor(dayName) : dayName);
        }
        return builder.toString();
    }
}
