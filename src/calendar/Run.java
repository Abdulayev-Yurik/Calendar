package calendar;

import java.util.Scanner;

/**
 * Created by yurik on 07.09.16.
 */
public class Run {

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Use default calendar ? 1 - Yes , 2 - No :");
        if (scanner.nextInt() == 1){
            new PrintDefaultCalendar();
        }else {
            new PrintCustomCalendar();
        }
    }
}