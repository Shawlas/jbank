package org.jbank.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.time.ZoneId;

public class Movement extends TransactionFunc {
    private final @Range(from = 0, to = Long.MAX_VALUE) int id;
    private final @NotNull OffsetDateTime dateTime;
    private final ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    private final @Range(from = 0, to = Long.MAX_VALUE) double amount;
    private final @NotNull TransactionAccount transactionAccount;

    protected Movement(@Range(from = 0, to = Long.MAX_VALUE) int id, double amount, @NotNull TransactionAccount transactionAccount) {
        this.id = id;
        this.amount = amount;
        this.transactionAccount = transactionAccount;
        this.dateTime = OffsetDateTime.now(zoneId);
    }

    // Getters

    public @NotNull TransactionAccount getTransactionAccount() {
        return transactionAccount;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public double getAmount() {
        return amount;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public int getId() {
        return id;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public @NotNull OffsetDateTime getDateTime() {
        return dateTime;
    }


    @Override
    protected void addBalance(TransactionAccount transactionAccount, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("The withdraw value must no be Zero or less");
        } else {
            transactionAccount.balance = transactionAccount.balance + amount;
        }
    }

    @Override
    protected void removeBalance(TransactionAccount transactionAccount, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws Exception {

        if (transactionAccount.getBalance() < amount) {
            throw new Exception("The balance of this account is less than withdraw value");
        } else if (amount <= 0) {
            throw new Exception("The withdraw value must no be Zero or less");
        } else {
            transactionAccount.balance = transactionAccount.balance - amount;
        }

    }
}
