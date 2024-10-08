package ghostface.dev.movement;

import ghostface.dev.entities.Account;
import ghostface.dev.exception.TransactionException;
import org.jetbrains.annotations.NotNull;

public final class Deposit extends Transaction {

    public Deposit(@NotNull Account destination, double value) {
        super(Type.DEPOSIT, null, destination, value);
    }

    @Override
    public double calculate(@NotNull Account account) throws TransactionException {
        if (account.equals(getDestination())) {
            return account.getBalance() + getValue();
        }

        throw new TransactionException("This account doesn't match any account in the transaction");
    }
}
