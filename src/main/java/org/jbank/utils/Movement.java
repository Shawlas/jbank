package org.jbank.utils;
import org.jetbrains.annotations.NotNull;

import java.time.*;

public class Movement {
    private final @NotNull OffsetDateTime dateTime;
    private final @NotNull ZoneOffset zone = ZoneOffset.of("-03:00");
    private final @NotNull Customer customer;

    public Movement(@NotNull Customer customer) {
        this.customer = customer;
        this.dateTime = OffsetDateTime.now(getZone());
    }


    // getters and setters

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
