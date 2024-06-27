package org.jbank.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

public final class Customer {
    private final @NotNull String name;
    private final @NotNull String surname;
    private final @NotNull String id;
    private final @NotNull String number;
    private final @NotNull String email;
    private @Range(from = 0, to = Long.MAX_VALUE) double balance;


    public Customer(@NotNull String name, @NotNull String surname, @NotNull String id, @NotNull String number, @NotNull String email) {
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.surname = Objects.requireNonNull(surname, "Surname cannot be null");
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.number = Objects.requireNonNull(number, "Number cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
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
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }


    /*
     *
     * ID,
     * EMAIL AND
     * NUMBER NEED BE UNIQUE
     *
     * */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(number, customer.number) && Objects.equals(email, customer.email);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, number, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }

}
