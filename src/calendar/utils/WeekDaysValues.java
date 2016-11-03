package calendar.utils;

import java.util.List;
import java.util.Locale;

/**
 * Created by employee on 11/3/16.
 */
public class WeekDaysValues extends CalendarUtils{

    public static String getWeekDays(int numberWeek, int firstDayOfWeek,
                                     int monthLength, int thisDay, Locale locale, List weekends) {
        if (numberWeek == 0) {
            return getFirstWeek(firstDayOfWeek, thisDay, weekends);
        } else {
            return getAnotherWeek(7 * (numberWeek) - firstDayOfWeek + 2, thisDay, weekends, monthLength);
        }
    }

    private static String getAnotherWeek(int firstDayOfWeek, int thisDay, List weekends, int monthLength) {
        StringBuilder builder = new StringBuilder();
        int counter = firstDayOfWeek;
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            if (counter > monthLength) break;
            builder.append(getColorDay(numberDay, counter, thisDay, weekends));
            counter++;
        }
        return builder.toString();
    }

    private static String getFirstWeek(int firstDayOfWeek, int thisDay, List weekends) {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (int numberDay = 1; numberDay <= DAYS_IN_WEEK; numberDay++) {
            if (numberDay >= firstDayOfWeek) {
                builder.append(getColorDay(numberDay, counter, thisDay, weekends));
                counter++;
            } else {
                builder.append(getNumberDay(""));
            }
        }
        return builder.toString();
    }

    private static String getColorDay(int numberDay, int dayValue, int thisDay, List weekends) {
        String day = getNumberDay(Integer.toString(dayValue));
        if (dayValue == thisDay) {
            return CalendarUtils.toThisDayColor(day);
//        } else if (CalendarUtils.isWeekend(numberDay, weekends)) {
//            return CalendarUtils.toWeekendColor(getNumberDay(Integer.toString(dayValue)));
        } else {
            return getNumberDay(Integer.toString(dayValue));
        }
    }

    private static String getNumberDay(String numberDay) {
        return String.format(CalendarUtils.TYPICAL_STRING_FORMAT, numberDay);
    }

}
