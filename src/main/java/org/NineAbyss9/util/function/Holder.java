
package org.NineAbyss9.util.function;

import org.NineAbyss9.util.Option;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface Holder<T> extends Supplier<T>
{
    T get();

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
        return get() == null ? t.get() : get();
    }

    /**
     * If a value is present, returns the value, otherwise returns
     * {@code other}.
     *
     * @param other the value to be returned, if no value is present.
     *        May be {@code null}.
     * @return the value, if present, otherwise {@code other}
     */
    default T orElse(T other)
    {
        if (this.isPresent())
            return get();
        else
            return other;
    }

    /**@see Optional#stream()*/
    default Stream<T> stream()
    {
        if (isPresent())
            return Stream.of(get());
        else
            return Stream.empty();
    }

    /**
     * If a value is present, returns the value, otherwise returns
     * {@code null}.
     *
     * @return the value, if present, otherwise {@code null}
     */
    default T getIf(boolean flag) {
        return ifOrElse(flag, null);
    }


    /**
     * If a {@code flag} is {@code true} returns the value, otherwise returns
     * {@code  other}.
     *
     * @param other the value to be returned, if no value is present.
     * May be {@code null}.
     *
     * @return the value, if present, otherwise {@code other}
     */
    default T ifOrElse(boolean flag, T other)
    {
        if (flag)
            return this.get();
        else
            return other;
    }

    /**@see Optional#ifPresent(Consumer)*/
    default void ifPresent(Consumer<? super T> consumer) {
        if (isPresent())
            consumer.accept(get());
    }

    /**@see Optional#ifPresentOrElse(Consumer, Runnable)*/
    default void ifPresentOrElse(Consumer<? super T> consumer, Runnable runnable) {
        if (isPresent())
            consumer.accept(get());
        else
            runnable.run();
    }

    default boolean isPresent()
    {
        return get() != null;
    }

    default boolean isEmpty()
    {
        return get() == null;
    }

    static <T> Holder<T> newHolder(T value) {
        return new SimpleImp<>(value);
    }

    public static class SimpleImp<T> implements Holder<T> {
        private final T value;

        public SimpleImp(T pValue) {
            this.value = pValue;
        }

        public T get() {
            return value;
        }
    }
}
