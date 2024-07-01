package org.jbank.utils;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import java.util.Random;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.random.RandomGenerator;

final class Movement {
    private final @Range(from = 0, to = Long.MAX_VALUE) int id;
    private final @NotNull OffsetDateTime dateTime;
    private final @NotNull TransactionAccount transactionAccount;
    private final @Range(from = 0, to = Long.MAX_VALUE) double amount;
    private final @NotNull String operationType;
    private boolean isUsed = false;

    Movement(@NotNull TransactionAccount from, @Range(from = 0, to = Long.MAX_VALUE) double amount, TransactionAccount to) {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        int upperbound = 1000;
        Random random = new Random();

        this.id = random.nextInt(upperbound);
        this.transactionAccount = from;
        this.amount = amount;

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        operationType = "Transference";

        this.dateTime = OffsetDateTime.now(zoneId);

    }

    Movement(@NotNull TransactionAccount from, @Range(from = 0, to = Long.MAX_VALUE) double amount) {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        int upperbound = 1000;
        Random random = new Random();

        this.id = random.nextInt(upperbound);
        this.transactionAccount = from;
        this.amount = amount;

        from.setBalance(from.getBalance() - amount);

        operationType = "Withdraw";


        this.dateTime = OffsetDateTime.now(zoneId);
    }

    Movement(@Range(from = 0, to = Long.MAX_VALUE) double amount,@NotNull TransactionAccount to) {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        int upperbound = 1000;
        Random random = new Random();

        this.id = random.nextInt(upperbound);
        this.transactionAccount = to;
        this.amount = amount;

        to.setBalance(to.getBalance() + amount);

        operationType = "Deposit";

        this.dateTime = OffsetDateTime.now(zoneId);
    }

    // Getters

    @Override
    public String toString() {
        return "Movement{" +
                "amount=" + amount +
                ", transactionAccount=" + transactionAccount +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public double getAmount() {
        return amount;
    }

    public boolean isUsed() {
        return isUsed;
    }

    void setUsed(boolean used) {
        isUsed = used;
    }

    public @NotNull TransactionAccount getTransactionAccount() {
        return transactionAccount;
    }

    @Range(from = 0, to = Long.MAX_VALUE)
    public int getId() {
        return id;
    }

    public @NotNull OffsetDateTime getDateTime() {
        return dateTime;
    }

}
