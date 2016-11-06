package calendar.utils;

/**
 * Created by employee on 11/3/16.
 */
public class WeekDaysValues extends CalendarUtils{

    public static String getWeekDays(int firstDayOfWeek,
                                     int monthLength) {
        getDays(monthLength);

        return null;
    }

    public static String[] getDays(int monthLength) {
        String[] days = new String[monthLength];
        for (int day = 1; day <= days.length; day++) {
            days[day - 1] = day + "";
        }
        return days;
    }

    private static boolean isThisMonth(int firstDayOfWeek, int monthLength, int day) {
        return day > firstDayOfWeek && day - firstDayOfWeek <= monthLength;
    }


//    private static String getColorDay(int numberDay, int dayValue, int thisDay, List weekends) {
//        String day = getFormattedDay(Integer.toString(dayValue));
//        if (dayValue == thisDay) {
//            return CalendarUtils.toThisDayColor(day);
////        } else if (CalendarUtils.isWeekend(numberDay, weekends)) {
////            return CalendarUtils.toWeekendConsoleColor(getFormattedDay(Integer.toString(dayValue)));
//        } else {
//            return getFormattedDay(Integer.toString(dayValue));
//        }
//    }



}
