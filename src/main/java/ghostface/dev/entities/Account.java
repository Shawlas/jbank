package ghostface.dev.entities;

import ghostface.dev.exception.TransactionException;
import ghostface.dev.movement.Transaction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private final long id;
    private final @NotNull Customer customer;
    private final @NotNull Stack<@NotNull Transaction> history = new Stack<>();
    private double balance;

    public Account(@NotNull Customer customer) {
        this.customer = customer;
        this.balance = 0;
        this.id = AccountGenerateID.getId();
    }

    // Getters

    public final long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }

    public @NotNull List<@NotNull Transaction> getHistory() {
        return history.reversed();
    }

    public void setBalance(@NotNull Transaction transaction) throws TransactionException {
        if (history.contains(transaction)) {
            throw new TransactionException("This account already has this transaction");
        } else synchronized (this) {
            this.balance = transaction.calculate(this);
            this.history.push(transaction);
        }
    }

    // Implementations

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Account account = (Account) object;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Generate ID

    private static final class AccountGenerateID {

        private static final AtomicLong id = new AtomicLong(0);

        public static long getId() {
            return id.incrementAndGet();
        }
    }
}
