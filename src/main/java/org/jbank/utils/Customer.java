package org.jbank.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

public final class Customer {
    private final @NotNull String name;
    private final @NotNull String surname;
    private final @NotNull String id;;
    private final @NotNull String number;
    private final @NotNull String email;
    private @Range(from = 0, to = Long.MAX_VALUE) double balance;

    public Customer(@NotNull String name, @NotNull String surname, @NotNull String id, @NotNull String number, @NotNull String email) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.number = number;
        this.email = email;
    }

    public @NotNull String getName() {
        return name;
    }

    public @NotNull String getSurname() {
        return surname;
    }

    public @NotNull String getId() {
        return id;
    }

    public @NotNull String getNumber() {
        return number;
    }

    public @NotNull String getEmail() {
        return email;
    }

    public @Range(from = 0, to = Long.MAX_VALUE) double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
