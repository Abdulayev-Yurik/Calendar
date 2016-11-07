package calendar.web;

import calendar.interfaces.Calendar;
import calendar.utils.CalendarUtils;
import calendar.utils.WeekdaysName;
import calendar.utils.WeekdaysValues;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public class WebCalendar implements Calendar {

    private static List<Integer> weekends;

    public WebCalendar(){

    }

    @Override
    public void print(int startWithCustomDay, LocalDate date, List<Integer> listWeekends) {
        weekends = listWeekends;
        parseDate(startWithCustomDay, date);
    }

    private void parseDate(int startWithCustomDay, LocalDate date) {
        LocalDate thisDate = LocalDate.of(date.getYear(),
                date.getMonth().getValue(), 1);

        buildCalendar(thisDate, date.getDayOfMonth(), startWithCustomDay);
    }

    private void buildCalendar(LocalDate thisDate, int currentDay, int startWithCustomDay) {
        try (PrintWriter writer = new PrintWriter("calendar.html")) {
            writer.println(getHTMLHeader());
            writer.println(getWeekends(startWithCustomDay));
            writer.println(getBody(thisDate, startWithCustomDay, currentDay));
            writer.println(getHTMLFooter());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getBody(LocalDate thisDate, int startWithCustomDay, int currentDay) {
        DayOfWeek dayOfWeek = thisDate.getDayOfWeek();
        StringBuilder days = new StringBuilder();
        days.append("<tr>\n");
        days.append(WeekdaysValues.getPreviousMonthDays(thisDate, startWithCustomDay, CalendarUtils.WEB_VIEW));
        days.append(WeekdaysValues.getMonthValues(thisDate, currentDay, dayOfWeek, weekends,
                startWithCustomDay, CalendarUtils.WEB_VIEW));
        days.append(WeekdaysValues.getNextMonthDays(startWithCustomDay, CalendarUtils.WEB_VIEW));
        days.append("</tr>");
        return days.toString();
    }

    private String getWeekends(int startWithCustomDay) {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>\n\t");
        for (DayOfWeek dayOfWeek : WeekdaysName.getWeekdays(startWithCustomDay)) {
            String dayName = CalendarUtils.getFormattedDay(WeekdaysName.getDayValue(dayOfWeek));
            builder.append(CalendarUtils.isWeekend(dayOfWeek.getValue(), weekends) ?
                    CalendarUtils.toWeekendColor(dayName, CalendarUtils.WEB_VIEW) : "<td>" + dayName + "</td>")
                    .append("\n")
                    .append("\t");
        }
        builder.append("\n</tr>\n");
        return builder.toString();
    }

    private String getHTMLFooter() {
        return "</table>\n" +
                "</body>\n" +
                "</html>";
    }

    private String getHTMLHeader() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        td.weekend{\n" +
                "            color: red;\n" +
                "        }\n" +
                "        td.currentDay{\n" +
                "            color: cyan;\n" +
                "        }\n" +
                "        td.anotherMonthColor{\n" +
                "            color: orange;\n" +
                "        }\n" +
                "        td{\n" +
                "            padding:5px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>";
    }
}
