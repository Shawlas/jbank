package ghostface.dev.movement;

import ghostface.dev.account.Account;
import ghostface.dev.exception.MovementException;
import org.jetbrains.annotations.NotNull;

public final class Deposit extends Movement {

    public Deposit(@NotNull Account from, double value) {
        super(from, value);
    }

    @Override
    public void execute() throws MovementException {
        if (getOrigin().getHistory().contains(this)) {
            throw new MovementException("Origin Account already has this transaction");
        }

        double valueOrigin = getOrigin().getBalance().doubleValue();

        getOrigin().getBalance().setValue(valueOrigin + getValue());
    }
}
