package ghostface.dev.movement;

import ghostface.dev.entities.Account;
import ghostface.dev.exception.TransactionException;
import org.jetbrains.annotations.NotNull;

public final class Withdrawal extends Transaction {

    public Withdrawal(@NotNull Account origin, double value) {
        super(Type.WITHDRAWAL, origin, null, value);
    }

    @Override
    public double compute(@NotNull Account account) throws TransactionException {
        if (account.equals(getOrigin())) {
            return account.getBalance() - getValue();
        }

        throw new TransactionException("This account doesn't match any account in the transaction");
    }
}
