package calendar.utils;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by employee on 11/2/16.
 */
public class WeekdaysName extends CalendarUtils {

    public static List<DayOfWeek> getWeekdays(int startDayIndex) {
        List<DayOfWeek> names = new ArrayList<>();
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            names.add(DayOfWeek.of(startDayIndex));
            startDayIndex = CalendarUtils.nextDay(startDayIndex);
        }
        return names;
    }

    @NotNull
    public static String getDayValue(DayOfWeek dayOfWeek) {
        return dayOfWeek
                .getDisplayName(TextStyle.SHORT, Locale.UK)
                .toUpperCase();
    }
}
