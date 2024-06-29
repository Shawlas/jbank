package org.jbank.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

public final class TransactionAccount {
    private final @NotNull String accountNumber;
    private final @NotNull Customer customer;
    @Range(from = 0, to = Integer.MAX_VALUE) double balance;

    public TransactionAccount(@NotNull Customer customer, @NotNull String accountNumber) {
        this.accountNumber = accountNumber;
        this.customer = customer;
    }


    public @NotNull Customer getCustomer() {
        return customer;
    }

    @Range(from = 0, to = Integer.MAX_VALUE)
    public double getBalance() {
        return balance;
    }

    public @NotNull String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionAccount account = (TransactionAccount) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(customer, account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, customer);
    }


}

abstract class TransactionFunc {

    abstract void addBalance(TransactionAccount transactionAccount, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws Exception;
    abstract void removeBalance(TransactionAccount transactionAccount, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws Exception;

}
