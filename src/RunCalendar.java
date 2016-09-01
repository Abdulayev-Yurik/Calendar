import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.Scanner;

/**
 * Created by yurik on 01.09.16.
 */
public class RunCalendar {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        System.out.print("Please enter month: ");
        Scanner scanner = new Scanner(System.in);
        String month = scanner.nextLine();

        if (month.isEmpty()) {
            parseDate(LocalDate.now().getMonth().getValue());
        } else {
            parseDate(Integer.parseInt(month));
        }
    }

    private static void parseDate(int month) {
        if (month <= 0 || month > 12) {
            System.out.println("incorrect");
            return;
        }
        LocalDate thisDate = LocalDate.of(LocalDate.now().getYear(), month, 1);
        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
        System.out.println("count = " + monthLength + ", first day from month in week = " + firstDayInWeek);
        generateView(monthLength, firstDayInWeek);
    }

    private static void generateView(int monthLength, int firstDayInWeek) {
        System.out.println("   П   В   С   Ч   П   " + colorPrint("С   Н"));
        int counter  = 1;
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 7; j++) {
                if(i == 1 && j >= firstDayInWeek) {
                    if (j >= 6) System.out.printf(ANSI_RED + "%4d" + ANSI_RESET, counter++);
                    else System.out.printf("%4d", counter++);
                }else if (i > 1 && monthLength >= counter) {
                    if (j >= 6) System.out.printf(ANSI_RED + "%4d" + ANSI_RESET, counter++);
                    else System.out.printf("%4d", counter++);
                }else
                    System.out.print("    ");
            }
            System.out.println();
        }
    }

    private static String colorPrint(String text){
        return ANSI_RED + text + ANSI_RESET;
    }
}