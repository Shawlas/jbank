package ghostface.dev.impl;

import ghostface.dev.entities.Account;
import ghostface.dev.entities.SavingsAccount;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Accounts implements Iterable<@NotNull Account> {
    // Todo: replace Account to Checking Account

    private final @NotNull List<@NotNull Account> array = new ArrayList<>();

    public boolean add(@NotNull Account account) {
        if (hasChecking()) return false;

        synchronized (this) {
            array.add(account);
            return true;
        }
    }

    public boolean add(@NotNull SavingsAccount account) {
        if (hasSavings()) return false;

        synchronized (this) {
            array.add(account);
            return true;
        }
    }

    public boolean remove(@NotNull Account account) {
        return array.remove(account);
    }

    public boolean remove(@NotNull SavingsAccount account) {
        return array.remove(account);
    }

    public boolean hasSavings() {
        return array.stream().anyMatch(account -> account.getClass().getName().equals(SavingsAccount.class.getName()));
    }

    public boolean hasChecking() {
        return array.stream().anyMatch(account -> account.getClass().getName().equals(Account.class.getName()));
    }

    @Override
    public @NotNull Iterator<@NotNull Account> iterator() {
        return array.iterator();
    }

    public @Nullable Account getAccount() {
        while (iterator().hasNext()) {
            @NotNull Account account = iterator().next();
            if (account.getClass().getName().equals(Account.class.getName())) {
                return account;
            }
        }
        return null;
    }

    public @Nullable SavingsAccount getSavingsAccount() {
        while (iterator().hasNext()) {
            @NotNull Account account = iterator().next();
            if (account.getClass().getName().equals(SavingsAccount.class.getName())) {
                return (SavingsAccount) account;
            }
        }
        return null;
    }
}
