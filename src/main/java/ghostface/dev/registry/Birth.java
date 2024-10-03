package ghostface.dev.registry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;

public final class Birth {

    @Range(from = 1, to = 31)
    private final int day;
    private final @NotNull Month month;
    private final @NotNull Year year;

    public Birth(@Range(from = 1, to = 31) int day, @NotNull Month month, @NotNull Year year) {
        if (!isValid(day, month, year)) throw new IllegalArgumentException("This is not a valid birth date");

        int age = Year.now().getValue() - year.getValue();
        if (age < 18) throw new IllegalArgumentException("You must be over 18");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    private boolean isMonth30(@NotNull Month month) {
        return Arrays.stream(Month30.values()).anyMatch(mounth30 -> mounth30.getValue() == month.getValue());
    }

    private boolean isValid(int day, @NotNull Month month, @NotNull Year year) {
        if (!(day >= 1 && day <= 31)) {
            return false;
        } else if (isMonth30(month) && day > 30) {
            return false;
        }

        if (month.getValue() == 2) {
            if (year.isLeap() && day > 29) {
                return false;
            } else return day <= 28;
        }

        if (year.getValue() > Year.now().getValue()) {
            return false;
        }

        return year.getValue() >= 1908;
    }

    // Getters

    @Range(from = 1, to = 31)
    public int getDay() {
        return day;
    }

    public @NotNull Month getMonth() {
        return month;
    }

    public @NotNull Year getYear() {
        return year;
    }

    public int getAge() {
        return Year.now().getValue() - getYear().getValue();
    }

    // Classes

    private enum Month30 {
        APRIL(4),
        JUNE(6),
        SEPTEMBER(9),
        NOVEMBER(11);

        private final int value;

        Month30(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
