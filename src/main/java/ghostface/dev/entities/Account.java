package ghostface.dev.entities;

import ghostface.dev.exception.TransactionException;
import ghostface.dev.movement.Deposit;
import ghostface.dev.movement.Transaction;
import ghostface.dev.movement.Transference;
import ghostface.dev.movement.Withdrawal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.YearMonth;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public abstract class Account {

    private final @NotNull Object lock = new Object();

    @Range(from = 0, to = Long.MAX_VALUE) private final long id;
    @Range(from = 0, to = Long.MAX_VALUE) private volatile double balance;
    private final @NotNull Customer customer;
    private final @NotNull Stack<@NotNull Transaction> history = new Stack<>();

    public Account(@NotNull Customer customer) {
        this.customer = customer;
        this.balance = 0;
        this.id = AccountGenerateID.getId();
    }

    public abstract @NotNull Withdrawal withdraw(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException;

    public abstract @NotNull Deposit deposit(@Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException;

    public abstract @NotNull Transference transfer(@NotNull Account account, @Range(from = 0, to = Long.MAX_VALUE) double amount) throws TransactionException;

    // Getters

    public final @Range(from = 0, to = Long.MAX_VALUE) long getId() {
        return id;
    }

    public @Range(from = 0, to = Long.MAX_VALUE) double getBalance() {
        return balance;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }

    public @NotNull Collection<@NotNull Transaction> getHistory() {
        return new HashSet<>(history.reversed());
    }

    public @NotNull Collection<@NotNull Transaction> getTransactionsThisMounth() {
        @NotNull YearMonth now = YearMonth.now();
        return getHistory()
                .stream()
                .filter(transaction -> transaction.getTime().getYear() == now.getYear() && transaction.getTime().getMonthValue() == now.getMonthValue())
                .collect(Collectors.toUnmodifiableSet());
    }

    public void setBalance(@NotNull Transaction transaction) throws TransactionException {
        if (history.contains(transaction)) {
            throw new TransactionException("This account already has this transaction");
        } else synchronized (lock) {
            this.balance = transaction.compute(this);
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

        private static final @NotNull AtomicLong id = new AtomicLong(0);

        public static @Range(from = 0, to = Long.MAX_VALUE) long getId() {
            return id.incrementAndGet();
        }
    }
}
