package ghostface.dev.impl;

import ghostface.dev.entities.Account;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Accounts implements Iterable<@NotNull Account> {

    private final @NotNull List<@NotNull Account> array = new ArrayList<>();
    private final @NotNull Object lock = new Object();

    private volatile boolean checking = false;
    private volatile boolean savings = false;

    public boolean add(@NotNull CheckingAccount account) {
        if (hasChecking()) return false;
        if (array.size() == 2) return false;

        synchronized (lock) {
            array.add(account);
            checking = true;
            return true;
        }
    }

    public boolean add(@NotNull SavingsAccount account) {
        if (hasSavings()) return false;
        if (array.size() == 2) return false;

        synchronized (lock) {
            array.add(account);
            savings = true;
            return true;
        }
    }

    public boolean remove(@NotNull CheckingAccount account) {
        synchronized (lock) {
            if (array.remove(account)) {
                checking = false;
                return true;
            }
        }

        return false;
    }

    public boolean remove(@NotNull SavingsAccount account) {
        synchronized (lock) {
            if (array.remove(account)) {
                savings = false;
                return true;
            }
        }

        return false;
    }

    public boolean hasSavings() {
        return savings;
    }

    public boolean hasChecking() {
        return checking;
    }

    @Override
    public @NotNull Iterator<@NotNull Account> iterator() {
        return array.iterator();
    }

    public @Nullable CheckingAccount getAccount() {
        return (CheckingAccount) array.stream().filter(account -> account instanceof CheckingAccount).findFirst().orElse(null);
    }

    public @Nullable SavingsAccount getSavingsAccount() {
        return (SavingsAccount) array.stream().filter(account -> account instanceof SavingsAccount).findFirst().orElse(null);
    }
}
