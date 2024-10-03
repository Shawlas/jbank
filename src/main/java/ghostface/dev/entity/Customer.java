package ghostface.dev.entity;

import ghostface.dev.impl.Accounts;
import ghostface.dev.registry.*;
import org.jetbrains.annotations.NotNull;

public interface Customer {

    @NotNull Name getName();

    @NotNull CPF getCPF();

    @NotNull Email getEmail();

    @NotNull PhoneNumber getNumber();

    @NotNull Birth getBirth();

    @NotNull Accounts getAccounts();
}
