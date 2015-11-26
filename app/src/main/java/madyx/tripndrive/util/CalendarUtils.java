package madyx.tripndrive.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by mahmoudreda on 26/11/15.
 */
public class CalendarUtils {

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Calendar timestampToCalendar(long timestamp) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());

        cal.setTimeInMillis(timestamp);
        return cal;
    }

    public static String calendarToString(Calendar calendar, String dateFormatPattern) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        Date date = calendar.getTime();
        SimpleDateFormat ft = new SimpleDateFormat(dateFormatPattern);
        return ft.format(date);
    }

    public static Calendar stringToCalendar(String dateStr, String dateFormatPattern) {
        boolean success = false;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPattern, Locale.getDefault());

        try {
            cal.setTime(sdf.parse(dateStr));
            success = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return success ? cal : null;
    }

    public static Calendar getEndOfDay(Calendar date) {
        Calendar calendar = (Calendar) date.clone();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar;
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);
        return getEndOfDay(calendar).getTime();
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTime(date);
        return getStartOfDay(calendar).getTime();
    }

    public static Calendar getStartOfDay(Calendar date) {
        Calendar calendar = (Calendar) date.clone();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * Calculate number of date between two dates.
     * @param startDate
     * @param endDate
     * @return number of days between the dates. If between startDate and endDate is less then 24h returns 0. If start < end, returns negative value
     */
    public static long getDaysBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return Math.abs(TimeUnit.MILLISECONDS.toDays(end - start));
    }

    public static long getHoursBetween(Calendar startDate, Calendar endDate) {
        long end = endDate.getTimeInMillis();
        long start = startDate.getTimeInMillis();
        return TimeUnit.MILLISECONDS.toHours(Math.abs(end - start));
    }

    public static int getMonthsBetween(Calendar start, Calendar end) {
        int diffYear = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        return diffMonth;
    }

    public static boolean isDateInCurrentDay(Calendar targetCalendar) {
        Calendar currentCalendar = Calendar.getInstance();
        int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);

        int targerDay = targetCalendar.get(Calendar.DAY_OF_MONTH);
        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);

        return day == targerDay && week == targetWeek && year == targetYear;
    }

    public static boolean isDateInCurrentWeek(Calendar targetCalendar) {
        Calendar currentCalendar = Calendar.getInstance();
        int week = currentCalendar.get(Calendar.WEEK_OF_YEAR);
        int year = currentCalendar.get(Calendar.YEAR);

        int targetWeek = targetCalendar.get(Calendar.WEEK_OF_YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);

        return week == targetWeek && year == targetYear;
    }

    public static boolean isDateInCurrentMonth(Calendar targetCalendar) {
        Calendar currentCalendar = Calendar.getInstance();
        int month = currentCalendar.get(Calendar.MONTH);
        int year = currentCalendar.get(Calendar.YEAR);

        int targetMonth = targetCalendar.get(Calendar.MONTH);
        int targetYear = targetCalendar.get(Calendar.YEAR);

        return month == targetMonth && year == targetYear;
    }

    public static boolean isDateInCurrentYear(Calendar targetCalendar) {
        Calendar currentCalendar = Calendar.getInstance();

        int year = currentCalendar.get(Calendar.YEAR);
        int targetYear = targetCalendar.get(Calendar.YEAR);

        return year == targetYear;
    }

    public static boolean isSameDay(Calendar dayA, Calendar dayB) {
        return dayA.get(Calendar.YEAR) == dayB.get(Calendar.YEAR) && dayA.get(Calendar.DAY_OF_YEAR) == dayB.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isDateInRange(Calendar day, Calendar startDay, Calendar endDay) {
        return startDay.before(day) && endDay.after(day);
    }

}
