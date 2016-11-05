package calendar.utils;

import org.jetbrains.annotations.NotNull;

import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by employee on 11/2/16.
 */
public class WeekdaysName extends CalendarUtils {

    public static List<String> getWeekdaysName(Locale locale, int dayValue) {
        List<String> names = new ArrayList<>();
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            String formatName = String.format(TYPICAL_STRING_FORMAT, getDayValue(locale, dayValue - 1));
            names.add(formatName);
            dayValue = nextDay(dayValue);
        }
        return names;
    }

    @NotNull
    private static String getDayValue(Locale locale, int numberDay) {
        return WeekFields.of(Locale.UK)
                .getFirstDayOfWeek()
                .plus(numberDay)
                .getDisplayName(TextStyle.SHORT, locale)
                .toUpperCase();
    }
}
