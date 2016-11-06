package calendar.interfaces;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by yurik on 05.11.16.
 */
public interface Calendar {

    void print(int startWithCustomDay, LocalDate currentDate, List<Integer> weekends);
}
