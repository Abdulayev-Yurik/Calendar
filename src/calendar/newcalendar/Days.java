package calendar.newcalendar;

/**
 * Created by employee on 11/4/16.
 */
public class Days {

    public void getDays(int firstDayOfWeek, int monthLength){
        int[] days = new int[monthLength];
        for (int i = 1; i < firstDayOfWeek + monthLength; i++) {
            days[i] = 0;
        }
    }
}
