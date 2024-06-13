package org.jbank.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.LocalDate;
import java.time.LocalTime;

public final class Deposit extends Movement {
    private final @Range(from = 0, to = Long.MAX_VALUE) double depositValue;

    public Deposit(@NotNull LocalDate date, @NotNull LocalTime time, @NotNull Customer customer, @Range(from = 0, to = Long.MAX_VALUE) double depositValue) {
        super(date, time, customer);
        this.depositValue = depositValue;
        toDeposit();
    }

    public void toDeposit() {
        getCustomer().setBalance(getCustomer().getBalance() + this.depositValue);
    }

}
