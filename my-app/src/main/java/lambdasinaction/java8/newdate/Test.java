package lambdasinaction.java8.newdate;

import net.sf.cglib.core.Local;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * java8日期api
 * LocalDate,LocalTime,LocalDateTime是方便人类使用的时间,
 * Period 是两个时间之间的一段时间 Period.
 * date.plus(Period)
 * Duration是时间线上两个瞬时时间点的距离,可以表示Instant直接的距离
     *  Instant start = Instant.now();
     *  doXXX();
     *  Instant end = Instant.now();
     *  Duration.between(start,end).toMillis();
 *
 */
public class Test {

    public static void main(String[] args) {
//        testLocalDate();
        testLocalDateTime();
//        testInstant();
//        testTemporalAdjuster();
//        testDateTimeFormatter();
    }

    public static void testLocalDate() {
        LocalDate date = LocalDate.of(2023, 4, 14);
        int year = date.getYear();
        Month month = date.getMonth();

        int day = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        int len = date.lengthOfMonth();
        int lengthOfYear = date.lengthOfYear();
        boolean leap = date.isLeapYear();

        System.out.println("year:"+year+",month:"+month.getValue()+",day:"+day+
                ",dayOfWeek[:"+dayOfWeek.getValue()+","+dayOfWeek.name()+",lenOfMonth:"+len +",lenOfYear:"+lengthOfYear
        +",leap:"+leap);

        System.out.println(date.withYear(2022));
        System.out.println(date.withDayOfYear(365));
        System.out.println(date.withMonth(12));
        System.out.println(LocalDate.now());

        System.out.println(date.get(ChronoField.YEAR));
        System.out.println(date.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(date.get(ChronoField.DAY_OF_MONTH));
        System.out.println(date.get(ChronoField.DAY_OF_WEEK));

        LocalDate localDate = LocalDate.parse("2023-04-14"); //这里不能是2023-4-14"
        System.out.println(localDate);


    }

    public static void testLocalDateTime() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("hour:"+time.getHour() + ",minites:"+time.getMinute()+",sec:"+time.getSecond());

        LocalDate localDate = time.toLocalDate();
        LocalTime time1 = time.toLocalTime();

        System.out.println(localDate.plus(Period.ofDays(2)));
    }

    public static void testInstant() {
        //Instant是机器容易识别的类,不像LocalDateTime是人类容易识别的API,
        System.out.println(Instant.ofEpochSecond(3).toString());
        System.out.println(Instant.ofEpochSecond(3,1000000000l).toString());

        //可以通过Duration和Period类使用Instant

        LocalTime time1 = LocalTime.of(15, 23);
        LocalTime time2 = LocalTime.now();
        Duration durationTime = Duration.between(time1, time2);

        System.out.println("相差天数:"+durationTime.toDays());


        LocalDateTime dateTime1 = LocalDateTime.of(2023, 5, 1,15,25,22);
        LocalDateTime dateTime2 = LocalDateTime.now();
        Duration durationDateTime = Duration.between(dateTime1, dateTime2);

        System.out.println("相差天数:"+durationDateTime.toDays());

        LocalDate date1 = LocalDate.of(2023, 3, 2);
        LocalDate date2 = LocalDate.now();

//        Duration durationDate = Duration.between(date1, date2); //这个会报错
        Period tenDays = Period.between(date1, date2);
        System.out.println(date1.plus(tenDays));;

        Duration threeMinutes = Duration.ofMinutes(3);
        Duration threeMinutess = Duration.of(3, ChronoUnit.MINUTES);
        Period tenDayss = Period.ofDays(10);
        Period threeWeeks = Period.ofWeeks(3);
        Period twoYearsSixMonthOneDay = Period.of(2, 6, 1);
    }

    public static void testTemporalAdjuster() {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.with(nextOrSame(DayOfWeek.FRIDAY)));//调整为包括今天在内的下一个周六
        System.out.println(date.with(lastDayOfMonth()));//当月最后一天
        System.out.println(date.with(dayOfWeekInMonth(0,DayOfWeek.SATURDAY)));//当月的第一个星期六
        System.out.println(date.with(dayOfWeekInMonth(1,DayOfWeek.SATURDAY)));//当月的第一个星期六
        System.out.println(date.with(dayOfWeekInMonth(2,DayOfWeek.SATURDAY)));//当月的第2个星期六
        System.out.println(date.with(dayOfWeekInMonth(2,DayOfWeek.MONDAY)));//当月的第2个星期一


        System.out.println(date.with(lastInMonth(DayOfWeek.MONDAY)));//当月的最后一个星期一
        System.out.println(date.with(next(DayOfWeek.FRIDAY)));//当月的下一个星期五
        System.out.println(date.with(lastInMonth(DayOfWeek.FRIDAY)));//当月的最后一个星期五


        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow =
                            DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd = 1;
                    if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
                    if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
        date = date.with(nextWorkingDay);
        System.out.println("下一个工作日是:"+date);
    }

    public static void testDateTimeFormatter() {
        LocalDate date = LocalDate.of(2023, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);// 20230318
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);// 2023-03-18
        System.out.println(s1+"     "+s2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateStr = date.format(formatter);
        System.out.println(dateStr);

        LocalDate origin = LocalDate.parse(dateStr, formatter);
        System.out.println(origin.toString());
    }


}
