package org.jbank.services;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.Range;

public class WithdrawServices {

    public static void withdraw(TransactionAccount from, @Range(from = 0, to = Long.MAX_VALUE) double amount ) {
        if (amount <= 0 || from.getBalance() < amount) {
            throw new IllegalArgumentException("The amount in the bank account is zero or is less than the amount");
        } else {
            MovementServices.createWithdraw(from,amount);
        }
    }

}
