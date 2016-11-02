package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import static calendar.PrintToConsole.parseDate;
import static java.time.LocalDate.now;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by employee on 11/2/16.
 */
public class TestingCalendar extends TestedMethods {
    private static final String TYPICAL_STRING_FORMAT = "%4s";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void assertWeekend(){
        int thisDay = 2;
        for (int numberDay = 6; numberDay <= 7; numberDay++) {
            System.out.print(getTypeDay(1, numberDay, thisDay, numberDay + ""));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(TYPICAL_STRING_FORMAT, ANSI_RED + getDayName(6, Locale.ENGLISH)));
        builder.append(String.format(TYPICAL_STRING_FORMAT, ANSI_RED + getDayName(7, Locale.ENGLISH)));
        assertThat(builder.toString(), is(outContent.toString()));
    }

    @Test
    public void equalPrintOneDay() {
        int week = 0;
        int numberDay = 1;
        System.out.print(getTypeDay(week, numberDay, 1, getDayName(numberDay)));
        String expected = String.format(TYPICAL_STRING_FORMAT, getDayName(1, Locale.ENGLISH));
        assertThat(expected, equalTo(outContent.toString()));
    }

    @Test
    public void equalOutPrintOneWeek() {
        int numberWeek = 0;
        StringBuilder expected = new StringBuilder();
        for (int dayInWeek = 1; dayInWeek <= 7; dayInWeek++) {
            System.out.print(getTypeDay(numberWeek, 1, dayInWeek, getDayName(dayInWeek)));
            expected.append(String.format(TYPICAL_STRING_FORMAT, getDayName(dayInWeek, Locale.ENGLISH)));
        }
        assertEquals(expected.toString(), outContent.toString());
    }

    @Test
    public void assertWeekendColor(){
        int thisDay = 2;
        for (int numberDay = 6; numberDay <= 7; numberDay++) {
            System.out.print(getTypeDay(1, numberDay, thisDay, numberDay + ""));
        }
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(TYPICAL_STRING_FORMAT, ANSI_RED + getDayName(6, Locale.ENGLISH)));
        builder.append(String.format(TYPICAL_STRING_FORMAT, ANSI_RED + getDayName(7, Locale.ENGLISH)));
        assertThat(builder.toString(), is(outContent.toString()));
    }

    @Test
    public void equalColorPrint() {
        int week = 2;
        int numberDay = 1;
        System.out.print(getTypeDay(week, numberDay, 1, numberDay + ""));
        String expected = String.format(TYPICAL_STRING_FORMAT, ANSI_CYAN + getDayFormat(1 + "") + ANSI_RESET);
        assertThat(expected, equalTo(outContent.toString()));
    }

    @Test
    public void assertNameEquals() {
        assertThat(String.format(TYPICAL_STRING_FORMAT, ""), is("    "));
    }

    @Test
    public void equalsDayName() throws DateTimeException {
        int numberDay = 1;
        String dayName = DayOfWeek.of(numberDay).getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase();
        assertThat(dayName, notNullValue());
        assertThat(dayName, equalTo("MON"));
    }


}