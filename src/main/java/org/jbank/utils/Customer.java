package org.jbank.utils;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class Customer {
    private final @NotNull String name;
    private final @NotNull String surname;
    private final @NotNull String id;
    private final @NotNull String email;
    private double balance;

    public Customer(@NotNull String name, @NotNull String surname, @NotNull String id, @NotNull String email, double balance) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.email = email;
        this.balance = balance;
    }

    public void setBalance(double balance, boolean status) {
        if (status) {
            this.balance += balance;
        } else {
            this.balance -= balance;
        }
    }
}
