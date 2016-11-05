package calendar;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by yurik on 05.11.16.
 */
public class PrintCustomCalendar {

    PrintCustomCalendar(){

    }


    private static List<Integer> getWeekends(String weekends) {
        List<Integer> listWeekends = new ArrayList<>();
        if (weekends.isEmpty()) {
            return Arrays.asList(DayOfWeek.SATURDAY.getValue(), DayOfWeek.SUNDAY.getValue());
        }
        for (String s : weekends.split(",")) {
            listWeekends.add(parseInt(s));
        }
        return listWeekends;
    }

    public static int getFirsDayOfWeek(String day) {
        return day.isEmpty() ? 0 : parseInt(day);
    }
}
