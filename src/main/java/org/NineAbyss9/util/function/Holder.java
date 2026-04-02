
package org.NineAbyss9.util.function;

import org.NineAbyss9.util.Option;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public interface Holder<T> extends Supplier<T>
{
    default Option<T> option()
    {
        return Option.ofNullable(get());
    }

    default Optional<T> asOptional()
    {
        return Optional.ofNullable(get());
    }

    /**
     * If a value is present, returns the value, otherwise throws
     * {@code NoSuchElementException}.
     *
     * @return the non-{@code null} value described by this {@code Optional}
     * @throws NoSuchElementException if no value is present
     */
    default T orElseThrow()
    {
        if (isEmpty())
            throw new NoSuchElementException("No value present");
        return get();
    }

    default <X extends Throwable> T orElseThrow(X exc) throws X
    {
        T result = get();
        if (result == null)
            throw exc;
        return result;
    }

    default T orElseGet(Supplier<T> t)
    {
        T result;
        return (result = get()) == null ? t.get() : result;
    }

    default boolean isPresent()
    {
        return get() != null;
    }

    default boolean isEmpty()
    {
        return get() == null;
    }
}
