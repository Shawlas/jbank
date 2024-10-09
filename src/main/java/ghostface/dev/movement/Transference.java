package ghostface.dev.movement;

import ghostface.dev.entities.Account;
import ghostface.dev.exception.TransactionException;
import org.jetbrains.annotations.NotNull;

public final class Transference extends Transaction {

    public Transference(@NotNull Account origin, @NotNull Account target, double value) {
        super(Type.TRANSFERENCE, origin, target, value);
    }

    @Override
    public double compute(@NotNull Account account) throws TransactionException {
        if (account.equals(getOrigin())) {
            return account.getBalance() - getValue();
        } else if (account.equals(getTarget())) {
            return account.getBalance() + getValue();
        }

        throw new TransactionException("This account doesn't match any account in the transaction");
    }
}
