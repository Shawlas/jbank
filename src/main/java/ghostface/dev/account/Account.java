package ghostface.dev.account;

import ghostface.dev.entity.Customer;
import ghostface.dev.movement.Movement;
import ghostface.dev.movement.Balance;
import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public abstract class Account {

    private final @NotNull Customer customer;
    private final @NotNull Stack<@NotNull Movement> history = new Stack<>();
    private @NotNull Balance balance;

    public Account(@NotNull Customer customer) {
        this.customer = customer;
        this.balance = new Balance(0);
    }

    public @NotNull Balance getBalance() {
        return balance;
    }

    public @NotNull Customer getCustomer() {
        return customer;
    }

    public @NotNull Stack<@NotNull Movement> getHistory() {
        return history;
    }

    public void setBalance(@NotNull Movement movement) {
        if (history.contains(movement)) {
            throw new IllegalArgumentException("Operation already is executed");
        }

        this.balance = movement.execute();
        this.history.add(movement);
    }
}
