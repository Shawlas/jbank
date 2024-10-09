package ghostface.dev.entities;

import ghostface.dev.impl.Accounts;
import ghostface.dev.registry.*;
import org.jetbrains.annotations.NotNull;

public interface Customer {

    @NotNull String getName();

    @NotNull CPF getCPF();

    @NotNull Email getEmail();

    @NotNull BrazilianNumber getNumber();

    @NotNull Birth getBirth();

    @NotNull Accounts getAccounts();
}
