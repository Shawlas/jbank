package ghostface.dev.movement;

import ghostface.dev.account.Account;
import ghostface.dev.exception.MovementException;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.OffsetDateTime;

public abstract class Movement {

    // TODO: add ID
    private final @NotNull Account origin;
    private final @NotNull OffsetDateTime time;
    private final double value;

    protected Movement(@NotNull Account origin, double value) {
        this.origin = origin;
        this.value = value;
        this.time = OffsetDateTime.now();
    }

    public double getValue() {
        return value;
    }

    public @NotNull Account getOrigin() {
        return origin;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    public abstract void execute() throws MovementException;
}
