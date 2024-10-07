package ghostface.dev.impl;

import ghostface.dev.entities.Account;
import ghostface.dev.entities.Customer;
import ghostface.dev.exception.TransactionException;
import ghostface.dev.movement.Deposit;
import ghostface.dev.movement.Transference;
import ghostface.dev.movement.Withdrawal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.UnknownNullability;

import java.time.*;
import java.util.Optional;

public final class SavingsAccount extends Account {

    private int times;
    private final @NotNull Object lock = new Object();
    private @UnknownNullability LocalDateTime lastMovement;
    private @UnknownNullability LocalDateTime newDay;

    public SavingsAccount(@NotNull Customer customer) {
        super(customer);
    }

    @Override
    public @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        resetTimes();
        if (times == 5) {
            throw new TransactionException("This account has already reached the limit of transactions allowed this month");
        } else synchronized (lock) {
            @NotNull Withdrawal withdrawal = new Withdrawal(this, value);
            setBalance(withdrawal);
            uptadeTime();
            return withdrawal;
        }
    }

    @Override
    public @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        resetTimes();
        if (times == 5) {
            throw new TransactionException("This account has already reached the limit of transactions allowed this month");
        } else synchronized (lock) {
            @NotNull Deposit deposit = new Deposit(this, value);
            setBalance(deposit);
            uptadeTime();
            return deposit;
        }
    }

    @Override
    public @NotNull Transference transfer(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        resetTimes();
        if (times == 5) {
            throw new TransactionException("This account has already reached the limit of transactions allowed this month");
        } else synchronized (lock) {
            @NotNull Transference transference = new Transference(this, account, value);
            setBalance(transference);
            account.setBalance(transference);
            uptadeTime();
            return transference;
        }
    }

    private synchronized void resetTimes() {
        if (lastMovement.isEqual(newDay) || lastMovement.isAfter(newDay)) {
            times = 0;
        }
    }

    private void uptadeTime() {
        if (times == 5) return;

        lastMovement = LocalDateTime.now();
        int month = lastMovement.getMonthValue();
        newDay = newDay.withMonth(month == 12 ? 1 : month + 1);
        times++;
    }

    public @Range(from = 0, to = 5) int getTimes() {
        return times;
    }

    public @NotNull Optional<@NotNull LocalDateTime> getLastMovement() {
        return Optional.ofNullable(lastMovement);
    }

    public @NotNull Optional<@NotNull LocalDateTime> getNewDay() {
        return Optional.ofNullable(newDay);
    }
}
