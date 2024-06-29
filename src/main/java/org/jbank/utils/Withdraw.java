package org.jbank.utils;

import org.jbank.entities.Movement;
import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class Withdraw extends Movement {

    public Withdraw(@Range(from = 0, to = Long.MAX_VALUE) int id, double amount, @NotNull TransactionAccount transactionAccount) throws Exception {
        super(id, amount, transactionAccount);
        withdraw(transactionAccount, amount);
    }

    private void withdraw(TransactionAccount transactionAccount, @Range(from = 0, to = Long.MAX_VALUE) double amount ) throws Exception {
        removeBalance(transactionAccount, amount);
    }
}
