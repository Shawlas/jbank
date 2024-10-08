package ghostface.dev.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.*;

public final class Birth {

    public static boolean isAdult(int day, @NotNull Month month, @NotNull Year year) {
        @NotNull LocalDate birth =  MonthDay.of(month, day).atYear(Year.now().getValue());
        @NotNull LocalDate now = LocalDate.now();
        return birth.isEqual(now) || birth.isAfter(now);
    }

    public static boolean isValid(int day, @NotNull Month month, @NotNull Year year) {
        if (year.getValue() > Year.now().getValue()) {
            return false;
        } else if (day < 1 || day > 31) {
            return false;
        } else try {
            @NotNull YearMonth ym = YearMonth.of(year.getValue(), month);
            return day <= ym.lengthOfMonth();
        } catch (DateTimeException e) {
            return false;
        }
    }

    // Objects

    private final @Range(from = 1, to = 31) int day;
    private final @NotNull Month month;
    private final @NotNull Year year;

    public Birth(@Range(from = 1, to = 31) int day, @NotNull Month month, @NotNull Year year) {
        if (!isValid(day, month, year)) {
            throw new IllegalArgumentException("This is not a valid birth date");
        } else if (!isAdult(day, month, year)) { // Check the age
            throw new IllegalArgumentException("Customer must to be over 18");
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Getters

    public @Range(from = 1, to = 31) int getDay() {
        return day;
    }

    public @NotNull Month getMonth() {
        return month;
    }

    public @NotNull Year getYear() {
        return year;
    }

    public @NotNull MonthDay getMonthDay() {
        return MonthDay.of(month, day);
    }

    public @NotNull YearMonth getYearMonth() {
        return YearMonth.of(year.getValue(), month);
    }

    public int getAge() {
        @NotNull MonthDay now = MonthDay.now();
        @NotNull MonthDay birth = MonthDay.of(month, day);
        int age = Year.now().getValue() - year.getValue();
        return now.isBefore(birth) ? age - 1 : age;
    }
}
