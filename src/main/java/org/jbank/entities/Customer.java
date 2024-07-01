package org.jbank.entities;

import com.sun.source.tree.ReturnTree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Customer{
    private final @NotNull String id;
    private final @NotNull String name;
    private final @NotNull String surname;
    private final @NotNull String number;
    private final @NotNull String email;
    @Nullable TransactionAccount transactionAccount;

    public Customer(@NotNull String id, @NotNull String name, @NotNull String surname, @NotNull String number, @NotNull String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.email = email;
        this.transactionAccount = null;
    }

    private void constructStringValidation(String name, String surname, String id, String number, String email) {
        if (name.isEmpty() || surname.isEmpty() || id.isEmpty() || number.isEmpty() || email.isEmpty()) {
            throw new RuntimeException("Customer parameters constructor must no be Empty");
        }
    }

    public TransactionAccount getAccount() {
        if (transactionAccount == null) {
            throw new NullPointerException("This Customer don't have account yet");
        }
        return transactionAccount;
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

    /*
     *
     * ID,
     * EMAIL AND
     * NUMBER NEED BE UNIQUE
     *
     * */

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        @NotNull Customer customer = (Customer) o;
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
                '}';
    }
}
