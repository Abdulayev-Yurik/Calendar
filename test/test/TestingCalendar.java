package test;

import calendar.console.ConsoleCalendar;
import calendar.interfaces.Calendar;
import calendar.utils.CalendarUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by employee on 11/2/16.
 */
public class TestingCalendar {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Calendar currentCalendar = new ConsoleCalendar();
    private Calendar customCalendar;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void notEqualsTypeDay() {
        String s = "Monday";
        String coloredS = CalendarUtils.toAnotherMonthColor(s, CalendarUtils.CONSOLE_VIEW);
        assertThat(coloredS, not(equalTo(s)));
    }

    @Test
    public void equalPrintDaysName() {
        customCalendar = new ConsoleCalendar(LocalDate.now(), DayOfWeek.MONDAY,
                Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        System.out.print(currentCalendar.getWeekNames());
        assertThat(customCalendar.getWeekNames(), is(outContent.toString()));
    }

    @Test
    public void equalCalendar() {
        currentCalendar.print();
        customCalendar = new ConsoleCalendar(LocalDate.now(), DayOfWeek.MONDAY,
                Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        StringBuilder view = new StringBuilder().append("\t ")
                .append(customCalendar.getCurrentMonthHeader())
                .append("\n")
                .append(customCalendar.getWeekNames())
                .append("\n")
                .append(customCalendar.getMonthValues())
                .append("\n");
        assertThat(outContent.toString(), equalTo(view.toString()));
    }


}