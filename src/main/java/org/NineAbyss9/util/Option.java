
package org.NineAbyss9.util;

import org.NineAbyss9.util.function.Holder;

import java.util.*;
import java.util.function.*;

/**A container like {@linkplain Optional}, implements {@linkplain Supplier}, {@linkplain java.io.Serializable}.
 *
 * @see Optional
 *
 * @see Supplier
 *
 * @since 1.0.0
 *
 * @param <T> the type of the {@linkplain Option}
 *
 * @author NineAbyss*/
@SuppressWarnings("unused")
public class Option<T>
implements Holder<T>, java.io.Serializable {
    //SerialVersion
    @java.io.Serial
    private static final long serialVersionUID = 6561243608026846372L;
    /**The value of an {@linkplain Option}*/
    private final T value;

    /**
     * Constructs an instance with the described value.
     *
     * @param value the value to describe; it's the caller's responsibility to
     *        ensure the value is non-{@code null} unless creating the singleton
     *        instance returned by {@code empty()}.
     */
    Option(T value) {
        this.value = value;
    }

    /**Returns a new empty {@linkplain Option}
     *
     * @return a new empty {@linkplain Option}*/
    public static<T> Option<T> empty() {
        return new Option<>(null);
    }

    public static <T> Option<T> of(T value) {
        return new Option<>(Objects.requireNonNull(value));
    }

    public static <T> Option<T> ofNullable(T value) {
        return value == null ? new Option<>(null) : new Option<>(value);
    }

    /**{@inheritDoc}
     *
     *@return the {@linkplain Option#value} of an {@linkplain Option}*/
    public T get() {
        return this.value;
    }

    public T ifOrElseThrow(boolean flag, Supplier<Throwable> exception)
    throws Throwable {
        if (flag)
            return this.value;
        else
            throw exception.get();
    }

    /**@param consumer will always accept the value, but if the {@linkplain Option#value} is {@code null},
     *  may throw {@linkplain NullPointerException}
     *
     * @throws NullPointerException if the {@linkplain #value} is {@code null}
     */
    public void run(Consumer<? super T> consumer)
    throws NullPointerException {
        consumer.accept(value);
    }

    /**
     * If a value is present, and the value matches the given predicate,
     * returns an {@linkplain Option} describing the value, otherwise returns an
     * empty {@linkplain Option}.
     *
     * @param predicate the predicate to apply to a value, if present
     * @return an {@linkplain Option} describing the value of this
     *         {@linkplain Option}, if a value is present and the value matches the
     *         given predicate, otherwise an empty {@linkplain Option}
     * @throws NullPointerException if the predicate is {@code null}
     */
    public Option<T> filter(Predicate<? super T> predicate) {
        if (isEmpty())
            return this;
        else
            return predicate.test(value) ? this : Option.empty();
    }

    public Supplier<T> asSupplier() {
        return () -> value;
    }

    /**@see Optional#equals(Object)*/
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        else {
            if (obj instanceof Option<?> option)
                return Objects.equals(value, option.value);
            else {
                if (obj instanceof Optional<?> o)
                    return o.filter(object -> Objects.equals(object, value)).isPresent();
                return false;
            }
        }
    }

    /**@see Optional#hashCode() */
    public int hashCode() {
        return Objects.hashCode(value);
    }

    /**@see Optional#toString() */
    public String toString() {
        return value != null
                ? ("Option[" + value + "]")
                : "Option.empty";
    }

    public boolean isNaN() {
        if (value instanceof Double d)
            return d.isNaN();
        else if (value instanceof Float f)
            return f.isNaN();
        else
            return false;
    }
}
