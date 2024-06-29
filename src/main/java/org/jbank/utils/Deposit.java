package org.jbank.utils;

import org.jbank.entities.Movement;
import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public class Deposit extends Movement {

    public Deposit(@Range(from = 0, to = Long.MAX_VALUE) int id, double amount, @NotNull TransactionAccount transactionAccount) throws Exception {
        super(id, amount, transactionAccount);
        deposit(transactionAccount, amount);
    }

    private void deposit(TransactionAccount transactionAccount, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws Exception {
        addBalance(transactionAccount, amount);
    }

}
