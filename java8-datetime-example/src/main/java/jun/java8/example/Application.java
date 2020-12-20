package jun.java8.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class Application {

    private static final Logger logger = LogManager.getLogger();

    /**
     * The new Date and Time APIs/classes (JSR-310),
     * it will provide some highly demanded features such as:
     *
     * <ul>
     *     <li>
     *         All the key public classes are immutable and thread-safe
     *     </li>
     *     <li>
     *         Defined terminology and behavior that other areas in computing can adopt
     *     </li>
     * </ul>
     */
    public static void main(String[] args) {
        datetime_localDate();
        datetime_localTime();
        datetime_localDateTime();
        datetime_localDataTime_with_Zone();
        datetime_instant();
        datetime_duration();
        datetime_period();
        datetime_utility();
        datetime_clock();
    }

    /**
     * A date without a time-zone in the ISO-8601 calendar system,
     * such as 2007-12-03.
     */
    private static void datetime_localDate() {
        logger.info("localDate: -------------------");
        LocalDate date = LocalDate.now();
        logger.info("now date:{}", date);
        logger.info("now date + 12 days:{}", date.plusDays(12));

        logger.info("leap year:{}", date.isLeapYear());
        logger.info("day of year:{}", date.getDayOfYear());
        logger.info("day of month:{}", date.getDayOfMonth());
        logger.info("day of week(name):{}", date.getDayOfWeek());
        logger.info("day of week(number):{}", date.getDayOfWeek().getValue());
        logger.info("year:{}", date.getYear());
        logger.info("month(name):{}", date.getMonth());
        logger.info("month(number):{}", date.getMonth().getValue());
        logger.info("minimum:{}", LocalDate.MIN);
        logger.info("maximum:{}", LocalDate.MAX);
    }

    /**
     * A time without a time-zone in the ISO-8601 calendar system, such as 10:15:30.
     */
    private static void datetime_localTime() {
        logger.info("localTime: -------------------");
        LocalTime time = LocalTime.now();
        logger.info("now time:{}", time);
        logger.info("specify time:{}", LocalTime.of(11, 50));
        logger.info("hour:{}", time.getHour());
        logger.info("minute:{}", time.getMinute());
        logger.info("second:{}", time.getSecond());
        logger.info("millisecond:{}", time.getNano() / 1000_000);
        logger.info("microsecond:{}", time.getNano() / 1000);
        logger.info("nanosecond:{}", time.getNano());
        logger.info("minimum:{}", LocalDate.MIN);
        logger.info("maximum time:{}", LocalDate.MAX);
        logger.info("midnight:{}", LocalTime.MIDNIGHT);
        logger.info("noon:{}", LocalTime.NOON);
    }

    /**
     * A date-time without a time-zone in the ISO-8601 calendar system,
     * such as 2007-12-03T10:15:30.
     */
    private static void datetime_localDateTime() {
        logger.info("localDateTime: -------------------");
        LocalDateTime dateTime = LocalDateTime.now();
        logger.info("now date time:{}", dateTime);
        logger.info("specify date time:{}", LocalDateTime.of(
                2020, 12, 19, 12, 30, 15, 415_000_000));
        logger.info("year:{}", dateTime.getYear());
        logger.info("month(name):{}", dateTime.getMonth());
        logger.info("month(value):{}", dateTime.getMonth().getValue());
        logger.info("day of year:{}", dateTime.getDayOfYear());
        logger.info("day of month:{}", dateTime.getDayOfMonth());
        logger.info("day of week(name):{}", dateTime.getDayOfWeek());
        logger.info("day of week(number):{}", dateTime.getDayOfWeek().getValue());
        logger.info("hour:{}", dateTime.getHour());
        logger.info("minute:{}", dateTime.getMinute());
        logger.info("second:{}", dateTime.getSecond());
        logger.info("millisecond:{}", dateTime.getNano() / 1000_000);
        logger.info("microsecond:{}", dateTime.getNano() / 1000);
        logger.info("nanosecond:{}", dateTime.getNano());
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss.SSS")
                .toFormatter();
        logger.info("LocalDateTime to text:{}", dateTime.format(formatter));
        logger.info("Text to LocalDateTime:{}", LocalDateTime.parse(dateTime.format(formatter), formatter));
    }

    /**
     * A date-time with an offset from UTC/Greenwich in the ISO-8601 calendar system,
     * such as 2007-12-03T10:15:30+01:00.
     * <p>
     * If you want to use the date functionality with zone information,
     * then Lambda provide you extra 3 classes similar to above one i.e.
     * OffsetDate, OffsetTime and OffsetDateTime. Timezone offset can be
     * represented in “+08:00” or “Asia/Shanghai” formats. This is done via
     * using another class i.e. ZoneId.
     */
    private static void datetime_localDataTime_with_Zone() {
        logger.info("localDateTime with zone: -------------------");
        logger.info("using systemDefaultZone:{}", OffsetDateTime.now());
        logger.info("using \"Z\" formats:{}", OffsetDateTime.now(ZoneId.of("Z")));
        logger.info("using \"+00:00\" formats:{}", OffsetDateTime.now(ZoneId.of("+00:00")));
        logger.info("using \"+08:00\" formats:{}", OffsetDateTime.now(ZoneId.of("+08:00")));
        logger.info("using \"-08:00\" formats:{}", OffsetDateTime.now(ZoneId.of("-08:00")));
        logger.info("using \"Asia/Shanghai\" formats:{}", OffsetDateTime.now(ZoneId.of("Asia/Shanghai")));
        OffsetDateTime now = OffsetDateTime.now(ZoneId.of("Asia/Shanghai"));
        logger.info("OffsetDateTime to LocalDate:{}", now.toLocalDateTime());
        logger.info("OffsetDateTime to LocalTime:{}", now.toLocalTime());
        logger.info("OffsetDateTime to LocalDateTime:{}", now.toLocalDateTime());
        logger.info("ZonedDateTime:{}", ZonedDateTime.now(ZoneId.systemDefault()));
    }

    /**
     * An instantaneous point on the time-line.
     * This class models a single instantaneous point on the time-line.
     * This might be used to record event time-stamps in the application.
     * <p>
     * For representing the specific timestamp at any moment, the class needs to be used is Instant.
     * The Instant class represents an instant in time to an accuracy of nanoseconds. Operations on
     * can Instant include comparison to another Instant and adding or subtracting a duration.
     */
    private static void datetime_instant() {
        logger.info("instant: -------------------");
        logger.info("now():{}", Instant.now());
        logger.info("now(Clock.systemUTC()):{}", Instant.now(Clock.systemUTC()));
        logger.info("now(Clock.systemDefaultZone()):{}", Instant.now(Clock.systemDefaultZone()));
        logger.info("Instant to LocalDateTime with default zone:{}",
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        logger.info("Instant to LocalDateTime with \"Asia/Shanghai\" time zone:{}",
                LocalDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai")));
        logger.info("Instant to OffsetDateTime with default time zone:{}",
                OffsetDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        logger.info("Instant to OffsetDateTime with \"Asia/Shanghai\" time zone:{}",
                OffsetDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Shanghai")));
        logger.info("Instant.ofEpochMilli(System.currentTimeMillis()):{}",
                Instant.ofEpochMilli(System.currentTimeMillis()));
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd HH:mm:ss")
                .toFormatter();
        logger.info("Instant to text:{}", formatter.format(
                LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault())));

        logger.info("Instant plus 1 day:{}", Instant.now().plus(1, ChronoUnit.DAYS));
        logger.info("Instant plus 1 hour:{}", Instant.now().plus(1, ChronoUnit.HOURS));
        logger.info("Instant plus 1 minute:{}", Instant.now().plus(1, ChronoUnit.MINUTES));
        logger.info("Instant plus 1 second:{}", Instant.now().plus(1, ChronoUnit.SECONDS));
        logger.info("Instant plus 1 millisecond:{}", Instant.now().plus(1, ChronoUnit.MILLIS));
        logger.info("Instant plus 1 microsecond:{}", Instant.now().plus(1, ChronoUnit.MICROS));
        logger.info("Instant plus 1 nanosecond:{}", Instant.now().plus(1, ChronoUnit.NANOS));
    }

    /**
     * A time-based amount of time,
     * such as '34.5 seconds'.
     * <p>
     * Duration class is a whole new concept brought first time in java language.
     * It represents the time difference between two time stamps.
     */
    private static void datetime_duration() {
        logger.info("duration: -------------------");
        logger.info("Duration 1 day:{}", Duration.ofDays(1));
        logger.info("Duration 1 hour:{}", Duration.ofHours(1));
        logger.info("Duration 1 minute:{}", Duration.ofMinutes(1));
        logger.info("Duration 1 second:{}", Duration.ofSeconds(1));
        logger.info("Duration 1 millisecond:{}", Duration.ofMillis(1));
        logger.info("Duration 1 microsecond:{}", Duration.ofNanos(1000));
        logger.info("Duration 1 nanosecond:{}", Duration.ofNanos(1).toString());

        logger.info("Duration 1 day 1 hour 1 minute 1 second 1 millisecond 1 microsecond 1 nanosecond:{}",
                Duration.ZERO
                        .plus(1, ChronoUnit.DAYS)
                        .plus(1, ChronoUnit.HOURS)
                        .plus(1, ChronoUnit.MINUTES)
                        .plus(1, ChronoUnit.SECONDS)
                        .plus(1, ChronoUnit.MILLIS)
                        .plus(1, ChronoUnit.MICROS)
                        .plus(1, ChronoUnit.NANOS));

        logger.info("between now to now + 1 minute:{}",
                Duration.between(Instant.now(), Instant.now().plus(Duration.ofMinutes(1))));
    }

    /**
     * A date-based amount of time in the ISO-8601 calendar system,
     * such as '2 years, 3 months and 4 days'.
     * <p>
     * To interact with human, you need to get bigger durations which are presented with Period class.
     */
    private static void datetime_period() {
        logger.info("period: -------------------");
        logger.info("Period 1 year:{}", Period.ofYears(1));
        logger.info("Period 1 month:{}", Period.ofMonths(1));
        logger.info("Period 1 week:{}", Period.ofWeeks(1));
        logger.info("Period 1 day:{}", Period.ofDays(1));

        logger.info("Period 1 year 1 month 1 day:{}",
                Period.ZERO.plusYears(1).plusMonths(1).plusDays(1));
    }

    private static void datetime_utility() {
        logger.info("utility: -------------------");
        logger.info("monday:{} value:{}", DayOfWeek.MONDAY, DayOfWeek.MONDAY.getValue());
        logger.info("tuesday:{} value:{}", DayOfWeek.TUESDAY, DayOfWeek.TUESDAY.getValue());
        logger.info("wednesday:{} value:{}", DayOfWeek.WEDNESDAY, DayOfWeek.WEDNESDAY.getValue());
        logger.info("thursday:{} value:{}", DayOfWeek.THURSDAY, DayOfWeek.THURSDAY.getValue());
        logger.info("friday:{} value:{}", DayOfWeek.FRIDAY, DayOfWeek.FRIDAY.getValue());
        logger.info("saturday:{} value:{}", DayOfWeek.SATURDAY, DayOfWeek.SATURDAY.getValue());
        logger.info("sunday:{} value:{}", DayOfWeek.SUNDAY, DayOfWeek.SUNDAY.getValue());

        LocalDate now = LocalDate.now();
        logger.info("when was monday in current week:{}", now.with(DayOfWeek.MONDAY));
        logger.info("last day of year:{}", now.with(TemporalAdjusters.lastDayOfYear()));
        logger.info("last day of month:{}", now.with(TemporalAdjusters.lastDayOfMonth()));
        logger.info("last monday in month:{}", now.with(TemporalAdjusters.lastInMonth(DayOfWeek.MONDAY)));
        logger.info("next monday at current date:{}", now.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
    }

    /**
     * A clock providing access to the current instant, date and time using a time-zone.
     */
    private static void datetime_clock() {
        logger.info("clock: -------------------");
        logger.info("Clock.systemUTC:{}", Clock.systemUTC().getZone());
        logger.info("Clock.systemDefaultZone:{}", Clock.systemDefaultZone().getZone());
        logger.info("Clock.system(ZoneId.systemDefault()):{}",
                Clock.system(ZoneId.systemDefault()).getZone());
        logger.info("Clock.system(ZoneId.of(\"Asia/Shanghai\"):{}",
                Clock.system(ZoneId.of("Asia/Shanghai")).getZone());
    }
}
