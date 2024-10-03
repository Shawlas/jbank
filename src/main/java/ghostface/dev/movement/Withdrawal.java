package ghostface.dev.movement;

import ghostface.dev.account.Account;
import ghostface.dev.exception.MovementException;
import org.jetbrains.annotations.NotNull;

public final class Withdrawal extends Movement {

    public Withdrawal(@NotNull Account origin, double value) {
        super(origin, value);
    }

    @Override
    public void execute() throws MovementException {
        if (getOrigin().getHistory().contains(this)) {
            throw new MovementException("Something is wrong with the operation");
        }

        double value = getOrigin().getBalance().doubleValue();
        getOrigin().getBalance().setValue(value - getValue());
    }
}
