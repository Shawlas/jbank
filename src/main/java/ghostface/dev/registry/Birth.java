package ghostface.dev.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.*;
import java.util.Objects;

public final class Birth {

    public static boolean isAdult(int day, @NotNull Month month, int year) {
        if (year > Year.now().getValue())
            return false;

        @NotNull LocalDate birth = LocalDate.of(year, month, day);
        @NotNull LocalDate now = LocalDate.now();

        int age = now.getYear() - birth.getYear();

        if (now.isBefore(birth.withYear(now.getYear()))) {
            age --;
        }

        return age >= 18;
    }

    public static boolean isValid(int day, @NotNull Month month, int year) {
        if (year > Year.now().getValue()) {
            return false;
        } else if (day < 1 || day > 31) {
            return false;
        } else try {
            @NotNull LocalDate date = LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    // Objects

    private final @Range(from = 1, to = 31) int day;
    private final @NotNull Month month;
    private final @NotNull Year year;

    public Birth(@Range(from = 1, to = 31) int day, @NotNull Month month, @NotNull Year year) {
        if (!isValid(day, month, year.getValue())) {
            throw new IllegalArgumentException("This is not a valid birth date");
        } else if (!isAdult(day, month, year.getValue())) {
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

    public @NotNull LocalDate getLocalDate() {
        return LocalDate.of(year.getValue(), month, day);
    }

    public int getAge() {
        @NotNull LocalDate now = LocalDate.now();

        int age = now.getYear() - year.getValue();

        return now.isBefore(getLocalDate()) ? age - 1 : age;
    }

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Birth birth = (Birth) object;
        return day == birth.day && month == birth.month && Objects.equals(year, birth.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
