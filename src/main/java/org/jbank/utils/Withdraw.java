package org.jbank.utils;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.Range;

public final class Withdraw  {

    private Withdraw() {
        throw new UnsupportedOperationException();
    }

    public static void withdraw(TransactionAccount from, @Range(from = 0, to = Long.MAX_VALUE) double amount ) throws Exception {
        if (amount <= 0 || from.getBalance() < amount) {
            throw new Exception("The amount in the bank account is zero or is less than the amount");
        } else {
            Movement movement = new Movement(from, amount);
        }
    }

}
