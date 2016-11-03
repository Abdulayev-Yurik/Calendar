package calendar;

import org.jetbrains.annotations.NotNull;

import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import static calendar.CalendarUtils.toWeekendColor;
import static calendar.PrintToConsole.DAYS_IN_WEEK;
import static calendar.PrintToConsole.TYPICAL_STRING_FORMAT;

/**
 * Created by employee on 11/2/16.
 */
class WeekNames {

    String getWeekNames(Locale locale, List weekends) {
        StringBuilder list = new StringBuilder();
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            String name = String.format(TYPICAL_STRING_FORMAT, getDayName(locale, numberDay - 1));
            name = CalendarUtils.isWeekend(numberDay, weekends) ? toWeekendColor(name) : name;
            list.append(name);
        }
        return list.toString();
    }

    @NotNull
    private String getDayName(Locale locale, int numberDay) {
        return WeekFields.of(locale)
                .getFirstDayOfWeek()
                .plus(numberDay)
                .getDisplayName(TextStyle.SHORT, locale)
                .toUpperCase();
    }
}
