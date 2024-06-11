package org.jbank.utils;

import org.jetbrains.annotations.NotNull;

public class Deposit {
    private final @NotNull Customer customer;
    private boolean isAdd = true;

    public Deposit(@NotNull Customer customer) {
        this.customer = customer;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }

    public boolean getIsAdd() {
        return isAdd;
    }
}
