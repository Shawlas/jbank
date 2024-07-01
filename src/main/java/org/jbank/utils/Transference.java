package org.jbank.utils;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class Transference {

    private Transference() {
        throw new UnsupportedOperationException();
    }

    public static void transference(@NotNull TransactionAccount from, @Range(from = 0, to = Long.MAX_VALUE) double amount, TransactionAccount to) throws Exception {
        if (from.getBalance() < amount) {
            throw new Exception("The amount in the bank account is zero or is less than the amount");
        } else {
            Movement movement = new Movement(from, amount, to);
        }
    }

}
