package calendar;

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
        System.out.print("Please enter year: ");
        String year = scanner.nextLine();
        System.out.print("Please enter month: ");
        String month = scanner.nextLine();
        System.out.print("Please enter day: ");
        String day = scanner.nextLine();

        try {
            parseDate(parseInt(year), parseInt(month), parseInt(day));
        } catch (Exception e) {
            parseDate(now().getYear(), now().getMonth().getValue(), now().getDayOfMonth());
        }
    }
}
