package org.jbank.services;

import org.jbank.entities.TransactionAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import java.util.Random;

import java.time.OffsetDateTime;
import java.time.ZoneId;

final class MovementServices {
    private final @Range(from = 0, to = Long.MAX_VALUE) int id;
    private final @NotNull OffsetDateTime dateTime;
    private final @NotNull TransactionAccount transactionAccount;
    private final @Range(from = 0, to = Long.MAX_VALUE) double amount;
    private final @NotNull String operationType;
    private boolean isUsed = false;

    MovementServices(@NotNull String operationType , @NotNull TransactionAccount account, @Range(from = 0, to = Long.MAX_VALUE) double amount) {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        Random random = new Random();
        this.id = random.nextInt(1000);
        this.dateTime = OffsetDateTime.now(zoneId);
        this.transactionAccount = account;
        this.amount = amount;
        this.operationType = operationType;
    }

    // Statics

    static @NotNull MovementServices createDeposit(@NotNull TransactionAccount to, @Range(from= 0, to = Long.MAX_VALUE) double amount) {
        MovementServices movementServices = new MovementServices("Deposit",to, amount);
        to.setBalance(to.getBalance()+ amount);
        return movementServices;
    }

    static @NotNull MovementServices createWithdraw(@NotNull TransactionAccount to, @Range(from = 0, to = Long.MAX_VALUE) double amount) {
        if (to.getBalance() < amount) {
            throw new IllegalArgumentException("Without funds\n");
        }
        MovementServices movementServices = new MovementServices("Withdraw", to, amount);
        to.setBalance(to.getBalance() - amount);
        return movementServices;
    }

    static @NotNull MovementServices createTransference(@NotNull TransactionAccount from, TransactionAccount to, @Range(from = 0, to = Long.MAX_VALUE) double amount) {
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Without founds");
        }
        MovementServices movementServices = new MovementServices("Transference", from, amount);
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        return movementServices;
    }

    // getters


    public @NotNull String getOperationType() {
        return operationType;
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

    @Override
    public String toString() {
        return "Movement{" +
                "amount=" + amount +
                ", transactionAccount=" + transactionAccount +
                ", dateTime=" + dateTime +
                ", id=" + id +
                '}';
    }

}
