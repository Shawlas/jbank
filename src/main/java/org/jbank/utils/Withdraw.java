package org.jbank.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.LocalDate;
import java.time.LocalTime;

public final class Withdraw extends Movement {
    private final @Range(from = 0, to = Long.MAX_VALUE) double withdrawValue;

    public Withdraw(@NotNull LocalDate date, @NotNull LocalTime time, @NotNull Customer customer, @Range(from = 0, to = Long.MAX_VALUE) double withdrawValue) {
        super(date, time, customer);
        this.withdrawValue = withdrawValue;
        toWithdraw();
    }

    public void toWithdraw() {
        getCustomer().setBalance(getCustomer().getBalance() - this.withdrawValue);
    }

}
