package ghostface.dev.impl;

import ghostface.dev.entities.Customer;
import ghostface.dev.registry.Birth;
import ghostface.dev.registry.BrazilianNumber;
import ghostface.dev.registry.CPF;
import ghostface.dev.registry.Email;
import org.jetbrains.annotations.NotNull;

public class CustomerImpl implements Customer {

    private final @NotNull String name;
    private final @NotNull CPF cpf;
    private final @NotNull Birth birth;
    private final @NotNull Accounts accounts;

    // Todo add setNumber and setEmail
    private final @NotNull BrazilianNumber number;
    private final @NotNull Email email;

    public CustomerImpl (
            @NotNull String name,
            @NotNull CPF cpf,
            @NotNull Birth birth,
            @NotNull Accounts accounts,
            @NotNull BrazilianNumber number,
            @NotNull Email email
    ) {
        this.name = name;
        this.cpf = cpf;
        this.birth = birth;
        this.accounts = accounts;
        this.number = number;
        this.email = email;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull CPF getCPF() {
        return cpf;
    }

    @Override
    public @NotNull Email getEmail() {
        return email;
    }

    @Override
    public @NotNull BrazilianNumber getNumber() {
        return number;
    }

    @Override
    public @NotNull Birth getBirth() {
        return birth;
    }

    @Override
    public @NotNull Accounts getAccounts() {
        return accounts;
    }
}
