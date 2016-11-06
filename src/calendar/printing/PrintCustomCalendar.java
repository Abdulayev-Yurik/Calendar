package calendar.printing;

import calendar.console.ConsoleCalendar;
import calendar.interfaces.Calendar;
import calendar.web.WebCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

/**
 * Created by yurik on 05.11.16.
 */
public class PrintCustomCalendar {

    public PrintCustomCalendar(){
        innitCustomCalendar();
    }

    private void innitCustomCalendar() {
        Calendar calendar = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter type calendar : 1 - Console, 2 - Web Calendar");
        int type = scanner.nextInt();
        System.out.println("Enter year :");
        int year = scanner.nextInt();
        System.out.println("Enter number month :");
        int month = scanner.nextInt();
        System.out.println("Enter day of month :");
        int day = scanner.nextInt();
        System.out.println("Enter custom start day of week : ");
        int customStartDay = getFirsDayOfWeek(scanner.nextInt());
        System.out.println("Enter weekends: 1,2,3");
        List<Integer> weekends = getWeekends(new Scanner(System.in).nextLine());
        switch (type) {
            case 1:
                calendar = new ConsoleCalendar();
                break;
            case 2:
                calendar = new WebCalendar();
                break;
        }

        if (calendar != null) calendar.print(customStartDay, LocalDate.of(year, month, day), weekends);
    }


    private static List<Integer> getWeekends(String weekends) {
        List<Integer> listWeekends = new ArrayList<>();
        if (weekends.isEmpty()) {
            return Arrays.asList(DayOfWeek.SATURDAY.getValue(), DayOfWeek.SUNDAY.getValue());
        }
        for (String s : weekends.split(",")) {
            listWeekends.add(parseInt(s.trim()));
        }
        return listWeekends;
    }

    private static int getFirsDayOfWeek(int day) {
        return day > 0 && day <= 7 ? day : DayOfWeek.MONDAY.getValue();
    }
}
