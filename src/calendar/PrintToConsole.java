package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Formatter;
import java.util.Locale;


/**
 * Created by yurik on 01.09.16.
 */
class PrintToConsole {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final int DAYS_IN_WEEK = 7;
    private static final int MAX_WEEKS = 6;

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
        System.out.println(printDaysName());
        int counter = 1;
        for (int dayInFirsWeek = 1; dayInFirsWeek <= DAYS_IN_WEEK; dayInFirsWeek++) {
            if (dayInFirsWeek >= firstDayInWeek)
                printDay(dayInFirsWeek, counter++, day);
            else
                System.out.print("    ");
        }
        System.out.println();
        for (int week = 2; week <= MAX_WEEKS; week++) {
            for (int dayInWeek = 1; dayInWeek <= DAYS_IN_WEEK; dayInWeek++) {
                if (counter <= monthLength)
                    printDay(dayInWeek, counter++, day);
                else
                    break;
            }
            System.out.println();
        }
    }

    private static String printDaysName() {
        String daysName = "";
        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            String day = DayOfWeek.of(i).getDisplayName(TextStyle.SHORT, Locale.getDefault()).toUpperCase();
            if (i == DayOfWeek.SATURDAY.getValue() || i == DayOfWeek.SUNDAY.getValue())
                daysName += new Formatter().format(ANSI_RED + "%4s" + ANSI_RESET, day);
            else
                daysName += new Formatter().format("%4s", day);
        }
        return daysName;
    }

    private static void printDay(int dayInWeek, int counter, int day) {
        if (counter == day)
            System.out.printf(ANSI_CYAN + "%4d" + ANSI_RESET, counter);
        else if (dayInWeek >= DayOfWeek.SATURDAY.getValue())
            System.out.printf(ANSI_RED + "%4d" + ANSI_RESET, counter);
        else
            System.out.printf("%4d", counter);
    }
}