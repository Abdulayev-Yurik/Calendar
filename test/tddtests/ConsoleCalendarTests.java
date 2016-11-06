package tddtests;

import calendar.utils.WeekdaysValues;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by yurik on 05.11.16.
 */
public class ConsoleCalendarTests {

    @Test
    public void getCountWeeks(){
        assertThat(5, is(WeekdaysValues.getWeekDays(2, 30)));
    }

    @Test
    public void notEmptyElemnts(){
        assertThat(DayOfWeek.SUNDAY.plus(1), is(WeekdaysValues.getDays(30)));
    }
}
