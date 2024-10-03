package ghostface.dev;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractPair<F, S> implements Pair<F, S> {

    private @Nullable F first;
    private @Nullable S second;

    public AbstractPair(@Nullable F first, @Nullable S second) {
        this.first = first;
        this.second = second;
    }

    public AbstractPair() {
        this.first = null;
        this.second = null;
    }

    @Override
    public boolean addFirst(@NotNull F element) {
        if (this.first == null)
            return false;

        this.first = element;
        return true;
    }

    @Override
    public boolean addSecond(@NotNull S element) {
        if (this.second == null)
            return false;

        this.second = element;
        return true;
    }

    @Override
    public boolean addFirstIfPresent(@NotNull F first) {
        if (!containsFirst()) return false;

        this.first = first;
        return true;
    }

    @Override
    public boolean addSecondIfPresent(@NotNull S second) {
        if (!containsSecond()) return false;

        this.second = second;
        return true;
    }

    @Override
    public @NotNull Optional<@NotNull F> getFirst() {
        return first == null ? Optional.empty() : Optional.of(first);
    }

    @Override
    public @NotNull Optional<@NotNull S> getSecond() {
        return second == null ? Optional.empty() : Optional.of(second);
    }

    @Override
    public boolean removeFirst() {
        if (first == null)
            return false;

        this.first = null;
        return true;
    }

    @Override
    public boolean removesSecond() {
        if (second == null)
            return false;

        this.second = null;
        return true;
    }

    @Override
    public boolean isFull() {
        return first != null && second != null;
    }

    @Override
    public boolean isEmpty() {
        return this.first == null && this.second == null;
    }

    @Override
    public boolean containsFirst() {
        return this.first != null;
    }

    @Override
    public boolean containsSecond() {
        return this.second != null;
    }

    @Override
    public int size() {
        if (isEmpty()) return 0;
        if (isFull()) return 2;
        return 1;
    }

    @Override
    public void clear() {
        this.first = null;
        this.second = null;
    }

    @Override
    public @NotNull Stream<@NotNull F> getStreamFisrt() {
        return first == null ? Stream.empty() : Stream.of(first);
    }

    @Override
    public @NotNull Stream<@NotNull S> getStreamSecond() {
        return second == null ? Stream.empty() : Stream.of(second);
    }
}
