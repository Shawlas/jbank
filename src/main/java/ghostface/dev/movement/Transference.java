package ghostface.dev.movement;

import ghostface.dev.account.Account;
import ghostface.dev.exception.MovementException;
import org.jetbrains.annotations.NotNull;

public final class Transference extends Movement {

    private final @NotNull Account destination;

    public Transference(@NotNull Account origin, @NotNull Account destination, double value) {
        super(origin, value);
        this.destination = destination;
    }

    @Override
    public void execute() throws MovementException {
        if (getOrigin().getHistory().contains(this) || destination.getHistory().contains(this)) {
            throw new MovementException("Origin or Destination Account already has this transaction");
        }

        double valueOrigin = getOrigin().getBalance().doubleValue();
        double valueDestination = getDestination().getBalance().doubleValue();

        getOrigin().getBalance().setValue(valueOrigin + getValue());
        getDestination().getBalance().setValue(valueDestination + getValue());
    }

    public @NotNull Account getDestination() {
        return destination;
    }
}
