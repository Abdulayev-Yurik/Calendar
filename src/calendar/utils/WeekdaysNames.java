package calendar.utils;

import org.jetbrains.annotations.NotNull;

import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

/**
 * Created by employee on 11/2/16.
 */
public class WeekdaysNames extends CalendarUtils {

    public String getWeekNames(Locale locale, List weekends, int dayValue) {
        StringBuilder list = new StringBuilder();
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            String formatName = String.format(TYPICAL_STRING_FORMAT, getDayName(locale, dayValue - 1));
            formatName = CalendarUtils.isWeekend(dayValue, weekends) ? toWeekendColor(formatName) : formatName;
            list.append(formatName);
            dayValue = nextDay(dayValue);
        }
        return list.toString();
    }

    @NotNull
    private String getDayName(Locale locale, int numberDay) {
        return WeekFields.of(Locale.UK)
                .getFirstDayOfWeek()
                .plus(numberDay)
                .getDisplayName(TextStyle.SHORT, locale)
                .toUpperCase();
    }
}
