package ghostface.dev.impl;

import ghostface.dev.entities.Account;
import ghostface.dev.entities.Customer;
import ghostface.dev.exception.TransactionException;
import ghostface.dev.movement.Deposit;
import ghostface.dev.movement.Transference;
import ghostface.dev.movement.Withdrawal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class CheckingAccount extends Account {

    private final @NotNull Object lock = new Object();

    public CheckingAccount(@NotNull Customer customer) {
        super(customer);
    }

    @Override
    public @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0 || amount > getBalance()) {
            throw new TransactionException("The amount is zero or less than customer balance");
        } else synchronized (lock) {
            @NotNull Withdrawal withdrawal = new Withdrawal(this, amount);
            this.setBalance(withdrawal);
            return withdrawal;
        }
    }

    @Override
    public @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0 || amount > getBalance()) {
            throw new TransactionException("The amount is zero or less than customer balance");
        } else synchronized (lock) {
            @NotNull Deposit deposit = new Deposit(this, amount);
            this.setBalance(deposit);
            return deposit;
        }
    }

    @Override
    public @NotNull Transference transfer(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException {
        if (amount <= 0 || amount > getBalance()) {
            throw new TransactionException("The amount is zero or less than customer balance");
        } else synchronized (lock) {
            @NotNull Transference transference = new Transference(this, account, amount);
            this.setBalance(transference);
            account.setBalance(transference);
            return transference;
        }
    }
}
