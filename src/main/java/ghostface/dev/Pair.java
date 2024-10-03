package ghostface.dev;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;


public interface Pair<F, S> {

    boolean addFirst(@NotNull F first);

    boolean addSecond(@NotNull S seccond);

    boolean addFirstIfPresent(@NotNull F first);

    boolean addSecondIfPresent(@NotNull S second);

    @NotNull Optional<@NotNull F> getFirst();

    @NotNull Optional<@NotNull S> getSecond();

    boolean removeFirst();

    boolean removesSecond();

    boolean isFull();

    boolean isEmpty();

    boolean containsFirst();

    boolean containsSecond();

    int size();

    void clear();

    @NotNull Stream<@NotNull F> getStreamFisrt();

    @NotNull Stream<@NotNull S> getStreamSecond();
}
