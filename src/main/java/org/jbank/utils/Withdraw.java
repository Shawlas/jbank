package org.jbank.utils;

import org.jetbrains.annotations.NotNull;

public class Withdraw{
    private final @NotNull Customer customer;
    private boolean isAdd = false;

    public Withdraw(@NotNull Customer customer) {
        this.customer = customer;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }

    public boolean getIsAdd() {
        return isAdd;
    }
}
