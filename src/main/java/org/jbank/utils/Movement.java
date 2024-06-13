package org.jbank.utils;
import org.jetbrains.annotations.NotNull;

import java.time.*;

public class Movement {
    private final @NotNull LocalDate date;
    private final @NotNull LocalTime time;
    private final @NotNull ZoneOffset zone = ZoneOffset.of("-03:00");
    private OffsetDateTime dateTime;
    private final @NotNull Customer customer;

    public Movement(@NotNull LocalDate date, @NotNull LocalTime time, @NotNull Customer customer) {
        this.date = date;
        this.time = time;
        this.customer = customer;

        this.dateTime = OffsetDateTime.of(getDate(), getTime(), getZone());
    }



    // getters and setters

    public @NotNull LocalDate getDate() {
        return date;
    }

    public @NotNull LocalTime getTime() {
        return time;
    }

    public @NotNull ZoneOffset getZone() {
        return zone;
    }

    public @NotNull OffsetDateTime getDateTime() {
        return dateTime;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }
}
