package DateTime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DateTimeExamples {

    public static void run() {
        generateObjects();
        parsingDateTimeObjects();
        modifications();
        temporalAdjustments();
        modifications();
        truncatingObjects();
        timeZones();
        offsettingTimeZone();
        periods();
        durations();
    }

    //Examples of how to get date-time, date and time objects
    public static void generateObjects() {

        //DateTime
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(1999, Month.APRIL, 13, 23, 59, 59);
        LocalDateTime.parse("1956-11-03T10:15:30");

        //Date
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(1945, Month.APRIL, 1);
        LocalDate ld3 = LocalDate.parse("1956-11-03");

        //Time
        LocalTime lt1 = LocalTime.now();
        LocalTime lt2 = LocalTime.of(1, 59, 59);
        LocalTime lt3 = LocalTime.parse("10:15:30");
    }

    //How to parse values from a datetime object
    public static void parsingDateTimeObjects() {
        LocalDateTime ldt = LocalDateTime.now();

        //retrieving separate time and date components
        LocalDate d = ldt.toLocalDate();
        LocalTime t = ldt.toLocalTime();

        System.out.println("current month: " + ldt.getMonth());
        System.out.println("current day number: " + ldt.getDayOfMonth());
        System.out.println("current time: "
                + ldt.getHour() + ":"
                + ldt.getMinute() + ":"
                + ldt.getSecond()
        );
    }

    /*
    Date is immutable so modifications will return objects instead of mutating the date

    Modifications can be done in two ways using the explicit method,
    or using the ambiguous method plus with a time unit
     */
    public static void modifications() {
        LocalDateTime ldt = LocalDateTime.now();
        LocalDate birthday = ldt.withDayOfMonth(5).withYear(1998).toLocalDate();
        LocalDate hangover = birthday.plusDays(1);
        LocalDate backToWorkDay = hangover.plus(1, ChronoUnit.DAYS);
    }

    public static void temporalAdjustments() {
        LocalDateTime rightNow = LocalDateTime.now();

        LocalDateTime nextMonday = rightNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        LocalDateTime secondMonday = rightNow
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)) //if already monday do not move
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        int lastDayOfMonth = rightNow.
                with(TemporalAdjusters.lastDayOfMonth())
                .getDayOfMonth();

        int lastDayOfNextMonth = rightNow
                .plusMonths(1)
                .with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();

        System.out.println(
                "\n" + "Last day of this month: " + lastDayOfMonth + "\n" +
                "Last day of next month: " + lastDayOfNextMonth
        );
    }

    public static void truncatingObjects() {
        LocalDateTime rightNow = LocalDateTime.now();
        LocalDateTime truncatedTime = rightNow.truncatedTo(ChronoUnit.SECONDS);
    }

    public static void timeZones() {
        LocalDateTime rightNow = LocalDateTime.now();

        Comparator<String> byStringValue = (s1, s2) -> s1.compareTo(s2);

        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        List<String> sortedZoneIds = zoneIds.stream()
                .sorted(byStringValue)
                .collect(Collectors.toList());

        System.out.println("\n Time in time zones");
        print(rightNow.toString());

        for (String zoneId : sortedZoneIds) {
            ZoneId id = ZoneId.of(zoneId);
            ZonedDateTime zoned = ZonedDateTime.of(rightNow, id);
            print(zoned.toString());
            print("\n");
        }
    }

    public static void offsettingTimeZone() {
        ZoneOffset offset = ZoneOffset.of(String.valueOf(ZoneId.of("+02:00")));

        // changes offset, while keeping the same point on the timeline
        OffsetTime time = OffsetTime.now();

        // changes offset, while keeping the same point on the timeline
        OffsetTime sameTimeDifferentOffset = time.withOffsetSameInstant(offset);

        // changes the offset, and updates the point on the timeline
        OffsetTime changeTimeWithNewOffset = time.withOffsetSameLocal(offset);

        // Can also create new object with altered fields as before
        changeTimeWithNewOffset
                .withHour(3)
                .plusSeconds(2);
    }

    public static void periods() {
        LocalDate firstDayOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        Period oneMonthPeriod = Period.of(0, 1, 0);

        for (int i = 0; i < 24; i++) {
            print(firstDayOfMonth.toString());
            firstDayOfMonth = firstDayOfMonth.plus(oneMonthPeriod);
        }
    }

    public static void durations() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        Duration duration = Duration.between(today, tomorrow);
        print("\n");
        print("duration in hours between " + today + " and "
                + tomorrow + " => " + duration.toHours());
    }

    //endregion

    public static void print(String... outputs) {
        for (String output : outputs) {
            System.out.print(output + "\n");
        }
    }

    public static void print(Number... outputs) {
        for (Number output : outputs) {
            System.out.print(output + "\n");
        }
    }
}
