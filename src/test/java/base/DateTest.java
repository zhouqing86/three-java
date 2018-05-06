package base;

import net.sf.cglib.core.Local;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static org.junit.Assert.assertEquals;

//LocalDate和LocalTime和最基本的String一样，是不变类型，不但线程安全，而且不能修改。
public class DateTest {
    @Test
    public void testCreateLocalDate() throws Exception {
        LocalDate d1= LocalDate.of(2018, 4, 15);
        LocalDate d2 = LocalDate.parse("2018-04-15");
        assertEquals(0, d1.compareTo(d2));
    }

    @Test
    public void testFirstDayOfMonth() throws Exception {
        LocalDate d1 = LocalDate.of(2018, 4, 15);
        LocalDate firstDayOfMonth = d1.withDayOfMonth(1);
        assertEquals(LocalDate.parse("2018-04-01"), firstDayOfMonth);
    }

    @Test
    public void testLastDayOfMonth() throws Exception {
        LocalDate d1 = LocalDate.of(2018, 1, 15);
        LocalDate firstDayOfMonth = d1.with(TemporalAdjusters.lastDayOfMonth());
        assertEquals(LocalDate.parse("2018-01-31"), firstDayOfMonth);
    }

    @Test
    public void testPlusDays() throws Exception {
        LocalDate d1 = LocalDate.of(2018, 1, 31);
        LocalDate nextDay = d1.plusDays(1);
        assertEquals(LocalDate.parse("2018-02-01"), nextDay);
    }

    @Test
    public void testFirstMondayOfMonth() throws Exception {
        LocalDate d1 = LocalDate.of(2018, 5, 5);
        LocalDate firstMondayOfMonth = d1.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        assertEquals(LocalDate.parse("2018-05-07"), firstMondayOfMonth);
    }

    @Test
    public void testParseLocalTime() throws Exception {
        LocalTime time = LocalTime.parse("12:00:01.490");
        LocalTime timeWithoutNanos = time.withNano(0);
        assertEquals("12:00:01", timeWithoutNanos.toString());
    }
}
