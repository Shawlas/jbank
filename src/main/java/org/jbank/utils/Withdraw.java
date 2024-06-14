package org.jbank.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.LocalDate;
import java.time.LocalTime;

public final class Withdraw extends Movement {
    private final @Range(from = 0, to = Long.MAX_VALUE) double withdrawValue;

    public Withdraw(@NotNull Customer customer, @Range(from = 0, to = Long.MAX_VALUE) double withdrawValue) {
        super(customer);
        this.withdrawValue = withdrawValue;
    }

    public void toWithdraw() {
        getCustomer().setBalance(getCustomer().getBalance() - this.withdrawValue);
    }

}
