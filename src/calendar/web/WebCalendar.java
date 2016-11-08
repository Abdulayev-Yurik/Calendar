package calendar.web;

import calendar.interfaces.Calendar;
import calendar.utils.CalendarUtils;
import calendar.utils.WeekdaysName;
import calendar.utils.WeekdaysValues;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public class WebCalendar implements Calendar {

    private LocalDate thisDate;
    private DayOfWeek startWeek;
    private List<DayOfWeek> weekend;
    private LocalDate firstDayOfMonth;

    public WebCalendar() {
        this(LocalDate.now());
    }

    public WebCalendar(LocalDate thisDate) {
        this(thisDate, DayOfWeek.MONDAY);
    }

    public WebCalendar(LocalDate thisDate, DayOfWeek startWeek){
        this(thisDate, startWeek, Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
    }

    public WebCalendar(LocalDate thisDate, DayOfWeek startWeek, List<DayOfWeek> weekend) {
        this.thisDate = thisDate;
        this.startWeek = startWeek;
        this.weekend = weekend;
    }

    @Override
    public void print() {
        firstDayOfMonth = LocalDate.of(thisDate.getYear(),
                thisDate.getMonth().getValue(), 1);

        buildCalendar(thisDate.getDayOfMonth());
    }

    private void buildCalendar(int currentDay) {
        try (PrintWriter writer = new PrintWriter("calendar.html")) {
            writer.println(getHTMLHeader());
            writer.println(getWeekends(startWeek.getValue()));
            writer.println(getBody(currentDay));
            writer.println(getHTMLFooter());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getBody(int currentDay) {
        StringBuilder days = new StringBuilder();
        days.append("<tr>\n");
        days.append(WeekdaysValues.getPreviousMonthDays(firstDayOfMonth, startWeek.getValue(), CalendarUtils.WEB_VIEW));
        days.append(WeekdaysValues.getMonthValues(firstDayOfMonth, currentDay, weekend,
                startWeek.getValue(), CalendarUtils.WEB_VIEW));
        days.append(WeekdaysValues.getNextMonthDays(startWeek.getValue(), CalendarUtils.WEB_VIEW));
        days.append("</tr>");
        return days.toString();
    }

    private String getWeekends(int startWithCustomDay) {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr>\n\t");
        for (DayOfWeek dayOfWeek : WeekdaysName.getWeekdays(startWithCustomDay)) {
            String dayName = CalendarUtils.getFormattedDay(WeekdaysName.getDayValue(dayOfWeek));
            builder.append(CalendarUtils.isWeekend(dayOfWeek, weekend) ?
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
