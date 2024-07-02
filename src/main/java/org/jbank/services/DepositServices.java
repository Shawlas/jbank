package org.jbank.services;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.Range;

public class DepositServices{

    public static void deposit(TransactionAccount to, @Range(from = 0, to = Long.MAX_VALUE) double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("The amount must to be positive");
        } else {
            MovementServices.createDeposit(to, amount);
        }
    }

}
