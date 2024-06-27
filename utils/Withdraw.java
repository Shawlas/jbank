package org.jbank.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.LocalDate;
import java.time.LocalTime;

public final class Withdraw extends Movement {

    public Withdraw(long id, @NotNull Customer customer, double amount) {
        super(id, customer, amount);
        executeOperation();
    }

    private void executeOperation() {
        getCustomer().setBalance(getCustomer().getBalance() - getAmount());
    }
}
