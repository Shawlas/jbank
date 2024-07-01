package org.jbank.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

public final class TransactionAccount {
    private final @NotNull String accountNumber;
    private final @NotNull Customer customer;
    private @Range(from = 0, to = Integer.MAX_VALUE) double balance;

    public TransactionAccount(@NotNull Customer customer, @NotNull String accountNumber) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        getCustomer().transactionAccount = this;
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

    public void setBalance(@Range(from = 0, to = Integer.MAX_VALUE) double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull TransactionAccount account = (TransactionAccount) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(customer, account.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, customer);
    }


    @Override
    public String toString() {
        return "TransactionAccount{" +
                "accountNumber='" + accountNumber + '\'' +
                ", customer=" + customer +
                ", balance=" + balance +
                '}';
    }
}

