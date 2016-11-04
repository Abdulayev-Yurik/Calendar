package calendar;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static calendar.PrintToConsole.parseDate;
import static java.lang.Integer.parseInt;
import static java.time.LocalDate.now;

/**
 * Created by yurik on 07.09.16.
 */
public class Run {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Use default calendar ? 1 - Yes , 2 - No :");

        if (parseInt(scanner.nextLine()) == 1) {
            parseDate(now().getYear(), now().getMonth().getValue(),
                    now().getDayOfMonth(), getWeekends(""), 0);
        } else {
            System.out.println("Enter start day index: 1- Mon... 7 - Sun");
            int firstDay = getFirsDayOfWeek(scanner.nextLine());

            System.out.println("Please enter weekends, example: 1,2,3");
            List<Integer> weekends = getWeekends(scanner.nextLine());

            System.out.println("Please enter year: ");
            String year = scanner.nextLine();
            System.out.println("Please enter month: ");
            String month = scanner.nextLine();
            System.out.println("Please enter day: ");
            String day = scanner.nextLine();
            parseDate(parseInt(year), parseInt(month), parseInt(day), weekends, firstDay);
        }
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