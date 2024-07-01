package org.jbank.utils;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class Deposit {

    private Deposit() {
        throw new UnsupportedOperationException();
    }

    public static void deposit(TransactionAccount to, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("The amount in the bank account is zero");
        } else {
            Movement movement = new Movement(amount, to);
        }
    }

}
