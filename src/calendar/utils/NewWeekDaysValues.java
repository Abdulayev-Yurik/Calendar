package calendar.utils;

import java.util.List;

/**
 * Created by employee on 11/4/16.
 */
public class NewWeekDaysValues extends CalendarUtils {

    public static String getWeekDays(int firstDayOfWeek,
                                     int monthLength, int currentDay, List weekends) {
        StringBuilder builder = new StringBuilder();
        for (int numberDay = 1; numberDay < monthLength + firstDayOfWeek; numberDay++) {
            if (numberDay >= firstDayOfWeek){
                builder.append(setTypeDay(getDay(firstDayOfWeek -1, currentDay, weekends, numberDay)));
                if (numberDay % 7 == 0){
                    builder.append("\n");
                }
            }else {
                builder.append(setTypeDay(""));
            }
        }
//        System.out.println(builder.toString());
        return builder.toString();
    }

    private static String getDay(int firstDayOfWeek, int currentDay, List weekends, int numberDay) {
        return getColorDay(numberDay, numberDay - firstDayOfWeek, currentDay, weekends);
    }

    private static String getColorDay(int numberDay , int dayValue, int currentDay, List weekends) {
        String day = setTypeDay(Integer.toString(dayValue));
        if (dayValue == currentDay) {
            return CalendarUtils.toThisDayColor(day);
        } else if (CalendarUtils.isWeekend( getNumberDayOfWeek(numberDay) , weekends)) {
            return CalendarUtils.toWeekendColor(setTypeDay(Integer.toString(dayValue)));
        } else {
            return setTypeDay(Integer.toString(dayValue));
        }
    }

    private static int getNumberDayOfWeek(int numberDay) {
        return numberDay % 7 == 0 ? 7 : numberDay - 7 * (numberDay/7) ;
    }

    private static String setTypeDay(String numberDay) {
        return String.format(CalendarUtils.TYPICAL_STRING_FORMAT, numberDay);
    }
}
