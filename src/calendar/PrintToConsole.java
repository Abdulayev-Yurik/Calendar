package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.lang.String.format;


/**
 * Created by yurik on 01.09.16.
 */
class PrintToConsole {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS = 6;
    private static int counter = 1;

    static void parseDate(int year, int month, int day) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(year, month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
        generateView(monthLength, firstDayInWeek, day);
    }

    private static void generateView(int monthLength, int firstDayInWeek, int day) {
        for (int week = 0; week <= MAX_WEEKS; week++) {
            for (int dayInWeek = 1; dayInWeek <= DAYS_IN_WEEK; dayInWeek++) {
                if (week == 0)
                    printDay(dayInWeek, day, printDaysName(dayInWeek));
                else if (counter <= monthLength)
                    actionDay(firstDayInWeek, dayInWeek, week, day);
                else
                    break;
            }
            System.out.println();
        }
    }

    private static void actionDay(int firstDayInWeek, int dayInWeek, int week, int day) {
        if (week == 1) {
            if (dayInWeek >= firstDayInWeek) {
                printDay(dayInWeek, day, Integer.toString(counter));
                counter++;
            }else
                System.out.print(format("%4s", ""));
        } else{
            printDay(dayInWeek, day, Integer.toString(counter));
            counter++;
        }
    }

    private static String printDaysName(int dayNumber) {
        return DayOfWeek.of(dayNumber).getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase();
    }

    private static void printDay(int dayInWeek, int day, String value) {
        if (counter == day)
            System.out.print(format(ANSI_CYAN + "%4s" + ANSI_RESET, value));
        else if (dayInWeek == DayOfWeek.SATURDAY.getValue() || dayInWeek == DayOfWeek.SUNDAY.getValue())
            System.out.print(format(ANSI_RED + "%4s" + ANSI_RESET, value));
        else
            System.out.print(format("%4s", value));
    }
}