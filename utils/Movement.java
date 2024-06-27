package org.jbank.utils;
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Movement {
    private final long id;
    private final @NotNull OffsetDateTime dateTime;
    private final @NotNull ZoneOffset zone = ZoneOffset.of("-03:00");
    private final @NotNull Customer customer;
    private final double amount;

    Movement(long id, @NotNull Customer customer, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        this.id = id;
        this.customer = Objects.requireNonNull(customer, "Customer of Movement cannot be null");
        this.dateTime = OffsetDateTime.now(getZone());
        this.amount = amount;
    }



    // getters and setters


    public long getId() {
        return this.id;
    }

    public @NotNull ZoneOffset getZone() {
        return zone;
    }

    public double getAmount() {
        return amount;
    }

    public @NotNull OffsetDateTime getDateTime() {
        return dateTime;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }
}
