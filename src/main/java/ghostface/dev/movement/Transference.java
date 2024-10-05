package ghostface.dev.movement;

import ghostface.dev.entities.Account;
import ghostface.dev.exception.TransactionException;
import org.jetbrains.annotations.NotNull;

public final class Transference extends Transaction {

    public Transference(@NotNull Account origin, @NotNull Account destination, double value) {
        super(Type.TRANSFERENCE, origin, destination, value);
    }

    @Override
    public double calculate(@NotNull Account account) throws TransactionException {
        if (account.equals(getOrigin())) {
            return account.getBalance() - getValue();
        } else if (account.equals(getDestination())) {
            return account.getBalance() + getValue();
        }

        throw new TransactionException("This account doesn't match any account in the transaction");
    }
}
