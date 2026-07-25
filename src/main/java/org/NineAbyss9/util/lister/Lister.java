
package org.NineAbyss9.util.lister;

import org.NineAbyss9.util.*;
import org.NineAbyss9.value_holder.BooleanValueHolder;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.random.RandomGenerator;

public interface Lister<E> extends List<E>, Deque<E>, IXUtilUser
{
    boolean accept(int index, Consumer<? super E> action);

    <R> R apply(int index, Function<E, R> fun);

    boolean ifPresent(int index, Consumer<? super E> action);

    /**@return a {@linkplain BooleanValueHolder} with the element at the specified index if it exists, otherwise {@code false}
     * and {@code null}.<p>
     * Use {@linkplain BooleanValueHolder#getBool()} to check if the element exists.*/
    default BooleanValueHolder<E> tryGet(int index)
    {
        if (index >= size()) return new BooleanValueHolder<>(false, null);
        return new BooleanValueHolder<>(true, get(index));
    }

    /**@throws IndexOutOfBoundsException if the index is out of bounds
     *
     * @return the element at the specified index.*/
    default E getOrThrow(int index)
    {
        if (index < size()) {
            return this.get(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    E get(int index);

    E element();

    E peek();

    E poll();

    E peekLast();

    E peekFirst();

    boolean isEmpty();

    int size();

    BooleanValueHolder<E> addValue(E pValue);

    default Elements<E> elements()
    {
        return Elements.mutable(this);
    }

    default void ifExists(int index, Consumer<? super E> pAction)
    {
        if (index >= size()) return;
        pAction.accept(this.get(index));
    }

    /**Executes the given action for <strong>each element</strong> of this {@linkplain Lister} until all elements have been processed or
     *  the action throws an exception.
     *
     *  @see #ifExists(int, Consumer) */
    default void ifNotEmpty(Consumer<? super E> pAction)
    {
        if (this.isEmpty()) {
            return;
        }
        Iterator<E> iterator = iterator();
        while (iterator.hasNext())
            pAction.accept(iterator.next());
    }

    boolean contains(Object obj);

    /**Gets a sample of a {@linkplain Lister}*/
    default E sample(RandomGenerator random) {
        return this.get(random.nextInt(size() - 1));
    }

    default E sample() {
        return sample(ThreadLocalRandom.current());
    }

    default ImmutableSubLister<E> immutable() {
        return new ImmutableSubLister<>(this);
    }

    default Optional<E> checkedOptional(int index) {
        return Optional.ofNullable(this.get(index));
    }

    default Optional<E> withOptional(int index) {
        return Optional.of(this.get(index));
    }

    default Option<E> checkedOption(int index) {
        return Option.ofNullable(this.get(index));
    }

    default Option<E> withOption(int index) {
        return Option.of(this.get(index));
    }

    /**Please use the method with cation.
     *
     * @return a converted element at the specified index.*/
    default <T> T convert(int index) {
        return IXUtil.c.convert(this.get(index));
    }
}
