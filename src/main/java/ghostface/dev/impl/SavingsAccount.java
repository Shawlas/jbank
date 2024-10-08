package ghostface.dev.impl;

import ghostface.dev.entities.Account;
import ghostface.dev.entities.Customer;
import ghostface.dev.exception.TransactionException;
import ghostface.dev.movement.Deposit;
import ghostface.dev.movement.Transference;
import ghostface.dev.movement.Withdrawal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;


public final class SavingsAccount extends Account {

    public SavingsAccount(@NotNull Customer customer) {
        super(customer);
    }

    @Override
    public @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        if (this.getTransactionsThisMounth().size() >= 5) {
            throw new TransactionException("This account has already reached the limit of transactions allowed this month");
        } else synchronized (lock) {
            @NotNull Withdrawal withdrawal = new Withdrawal(this, value);
            setBalance(withdrawal);
            return withdrawal;
        }
    }

    @Override
    public @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        if (this.getTransactionsThisMounth().size() >= 5) {
            throw new TransactionException("This account has already reached the limit of transactions allowed this month");
        } else synchronized (lock) {
            @NotNull Deposit deposit = new Deposit(this, value);
            setBalance(deposit);
            return deposit;
        }
    }

    @Override
    public @NotNull Transference transfer(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double value) throws TransactionException {
        if (this.getTransactionsThisMounth().size() >= 5) {
            throw new TransactionException("This account has already reached the limit of transactions allowed this month");
        } else synchronized (lock) {
            @NotNull Transference transference = new Transference(this, account, value);
            setBalance(transference);
            account.setBalance(transference);
            return transference;
        }
    }
}
