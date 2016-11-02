package calendar;

import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

import static calendar.PrintToConsole.DAYS_IN_WEEK;
import static calendar.PrintToConsole.TYPICAL_STRING_FORMAT;

/**
 * Created by employee on 11/2/16.
 */
class WeekNames {

    String getWeekNames(Locale locale) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            list.append(String.format(TYPICAL_STRING_FORMAT, WeekFields.of(locale)
                    .getFirstDayOfWeek()
                    .plus(i)
                    .getDisplayName(TextStyle.SHORT, locale)
                    .toUpperCase()));
        }
        return list.toString();
    }
}
