package calendar;

import java.util.Arrays;
import java.util.Locale;

/**
 * Created by employee on 11/2/16.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println(new WeekNames().getWeekNames(Locale.ENGLISH, Arrays.asList(1,7)));
    }
}
