package DateTime;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DateTimeHandler {

    //Examples of how to get date-time, date and time objects
    public static void generateObjects() {

        //DateTime
        LocalDateTime ldt1 = LocalDateTime.now();
        LocalDateTime ldt2 = LocalDateTime.of(1999, Month.APRIL, 13, 24, 60, 60);
        LocalDateTime.parse("1956-11-03T10:15:30");

        //Date
        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(1945, Month.APRIL, 1);
        LocalDate ld3 = LocalDate.parse("1956-11-03");

        //Time
        LocalTime lt1 = LocalTime.now();
        LocalTime lt2 = LocalTime.of(1, 60, 60);
        LocalTime lt3 = LocalTime.parse("10:15:30");
    }

    //How to parse values from a datetime object
    public static void parsingDateTimeObjects() {
        LocalDateTime ldt = LocalDateTime.now();

        //retrieving separate time and date components
        LocalDate d = ldt.toLocalDate();
        LocalTime t = ldt.toLocalTime();

        Month month = ldt.getMonth();
        int day = ldt.getDayOfMonth();
        int seconds = ldt.getSecond();
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

        //using ambigious plus with a chronounit paramater
        LocalDate backToWorkDay = hangover.plus(1, ChronoUnit.DAYS);

    }

    //TODO: show what happens if it is the 31st of one month and you increase by one month, does it go to the last day of the previous?
    public static void temporalAdjustments() {
        LocalDateTime rightNow = LocalDateTime.now();

        LocalDateTime nextMonday = rightNow.with(TemporalAdjusters.next(DayOfWeek.MONDAY));

        LocalDateTime secondMonday = rightNow
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)) //if already monday do not move
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY));


        LocalDateTime lastDayOfMonth = rightNow.with(TemporalAdjusters.lastDayOfMonth());

        LocalDateTime lastDayOfNextMonth = rightNow
                .plusMonths(1)
                .with(TemporalAdjusters.lastDayOfMonth());
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

        print(rightNow.toString());

        for (String zoneId : sortedZoneIds) {
            ZoneId id = ZoneId.of(zoneId);
            ZonedDateTime zoned = ZonedDateTime.of(rightNow, id);
            print(zoned.toString());
        }
    }

    public static void OffsettingTimeZone() {
        ZoneOffset offset = ZoneOffset.of("+2:00");

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

            //remember that mutation returns objects, dates are immutable
            firstDayOfMonth = firstDayOfMonth.plus(oneMonthPeriod);
        }
    }

    public static void durations() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        Duration duration = Duration.between(today, tomorrow);
        print(duration.toHours());
    }

    public static void chronologies() {
        //TODO:
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
