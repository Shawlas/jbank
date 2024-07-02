package org.jbank.services;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class TransferenceServices {

    public static void transference(@NotNull TransactionAccount from, @Range(from = 0, to = Long.MAX_VALUE) double amount, TransactionAccount to) {
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Without founds");
        } else {
            MovementServices.createTransference(from, to, amount);
        }
    }

}
