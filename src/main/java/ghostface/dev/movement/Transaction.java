package ghostface.dev.movement;

import ghostface.dev.entities.Account;
import ghostface.dev.exception.TransactionException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Transaction {

    private final long id;
    private final @NotNull Type type;

    private final @NotNull Account origin;
    private final @Nullable Account target;

    private final @NotNull OffsetDateTime time;
    @Range(from = 0, to = Long.MAX_VALUE) private final double value;

    protected Transaction(
            @NotNull Type type,
            @NotNull Account origin,
            @Nullable Account target,
            @Range(from = 0, to = Long.MAX_VALUE) double value
    ) {
        this.id = MovementGenereteID.getId();
        this.type = type;
        this.origin = origin;
        this.target = target;
        this.value = value;
        this.time = OffsetDateTime.now();
    }

    public abstract double compute(@NotNull Account account) throws TransactionException;

    // Getters

    public double getValue() {
        return value;
    }

    public @NotNull Account getOrigin() {
        return origin;
    }

    public @NotNull OffsetDateTime getTime() {
        return time;
    }

    public @Nullable Account getTarget() {
        return target;
    }

    public final long getId() {
        return id;
    }

    public @NotNull Type getType() {
        return type;
    }

    // Implementations

    @Override
    public boolean equals(@Nullable Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        @NotNull Transaction that = (Transaction) object;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Classes

    public enum Type {
        DEPOSIT,
        WITHDRAWAL,
        TRANSFERENCE
    }

    private static final class MovementGenereteID {

        private static final @NotNull AtomicLong id = new AtomicLong(0);

        public static long getId() {
            return id.incrementAndGet();
        }

    }
}
