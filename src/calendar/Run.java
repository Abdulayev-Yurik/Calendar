package calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static calendar.PrintToConsole.parseDate;
import static java.lang.Integer.parseInt;
import static java.time.LocalDate.now;

/**
 * Created by yurik on 07.09.16.
 */
public class Run {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        int firstDay = scanner.nextInt();
        int firstDay = scanner.nextInt();

//        List weekends = getWeekends(scanner);
        List weekends = Arrays.asList(6,7);
        System.out.print("Please enter year: ");
        String year = scanner.nextLine();
        System.out.print("Please enter month: ");
        String month = scanner.nextLine();
        System.out.print("Please enter day: ");
        String day = scanner.nextLine();

        try {
            parseDate(parseInt(year), parseInt(month), parseInt(day), weekends, firstDay);
        } catch (Exception e) {
            parseDate(now().getYear(), now().getMonth().getValue(),
                    now().getDayOfMonth(), weekends, firstDay);
        }
    }

    private static List getWeekends(Scanner scanner) {
        List<Integer> list = new ArrayList<>();
        System.out.println("Please enter weekends, press 0 to finish");
        for (; ; ) {
            int value = scanner.nextInt();
            if (value == 0) break;
            list.add(value);
        }
        return list;
    }
}