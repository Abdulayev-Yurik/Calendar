package calendar;

import calendar.console.ConsoleCalendar;
import calendar.interfacec.Calendar;
import calendar.web.WebCalendar;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yurik on 01.09.16.
 */
class PrintDefaultCalendar {

    PrintDefaultCalendar(){
        List<Integer> weekends = Arrays.asList(DayOfWeek.SATURDAY.getValue(), DayOfWeek.SUNDAY.getValue(), DayOfWeek.MONDAY.getValue());
        int startWithDefaultDay = DayOfWeek.MONDAY.getValue();

        Calendar calendar = null;
        System.out.print("Enter print mode, 1-Console, 2-WEB page");
        switch (new Scanner(System.in).nextInt()) {
            case 1:
                calendar = new ConsoleCalendar();
                break;
            case 2:
                calendar = new WebCalendar();
                break;
        }
        if (calendar != null){
            calendar.print(startWithDefaultDay, weekends);
        }
    }
//
//    private static final int MAX_WEEKS = 6;
//
//    static void parseDate(int year, int month, int currentDay, List weekends, int firstDay) {
//        if (month <= 0 || month > 12) {
//            System.out.println("incorrect");
//            return;
//        }
//        LocalDate thisDate = LocalDate.of(year, month, 1);
//        int firstDayInWeek = thisDate.getDayOfWeek().getValue();
//        if (firstDay == 0){
//            firstDay = firstDayInWeek;
//        }
//        int monthLength = thisDate.getMonth().length(thisDate.isLeapYear());
//
//        System.out.println(generateView(monthLength, firstDayInWeek, currentDay, weekends, firstDay));
//    }
//
//    private static String generateView(int monthLength, int firstDayInWeek,
//                                       int currentDay, List weekends, int customFirstDay) {
//        StringBuilder builder = new StringBuilder();
//        builder.append(new WeekdaysName().getWeekdaysName(Locale.UK, weekends, firstDayInWeek)).append("\n");
////        for (int numberWeek = 0; numberWeek < MAX_WEEKS; numberWeek++) {
////            int v = CalendarUtils.backDay(customFirstDay - 1);                  // FIXME: 11/3/16 not correct
////            builder.append(WeekDaysValues.getWeekDays(numberWeek, v, monthLength,
////                    currentDay, Locale.UK, weekends))
////                    .append("\n");
////        }
//        builder.append(NewWeekDaysValues.getWeekDays(customFirstDay, monthLength, currentDay,weekends));
//        return builder.toString();
//    }
}