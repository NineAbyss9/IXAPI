
package org.NineAbyss9.util.lister;

import org.NineAbyss9.value_holder.BooleanValueHolder;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SubLister<E>
extends LinkedList<E>
implements Lister<E> {
    @java.io.Serial
    private static final long serialVersionUID = 7474719356862439922L;
    public SubLister() {
        super();
    }

    public SubLister(Collection<? extends E> c) {
        super(c instanceof ImmutableSubLister<? extends E> ?
                ((Supplier<Collection<? extends E>>)() -> {
                    throw new UnsupportedOperationException();
                }).get() : c);
    }

    public BooleanValueHolder<E> addValue(E pValue) {
        return new BooleanValueHolder<>(this.add(pValue), pValue);
    }

    public boolean accept(int index, Consumer<? super E> action) {
        BooleanValueHolder<E> holder = this.tryGet(index);
        if (holder.getBool()) {
            action.accept(holder.getValue());
            return true;
        }
        return false;
    }

    public <R> R apply(int index, Function<E, R> fun) {
        return fun.apply(this.get(index));
    }

    public boolean ifPresent(int index, Consumer<? super E> action) {
        E element = this.get(index);
        if (element == null) {
            return false;
        }
        action.accept(element);
        return true;
    }

    /**@return a new, empty {@linkplain SubLister}, it is mutable.*/
    public static <E> SubLister<E> empty() {
        return new SubLister<E>();
    }

    @SafeVarargs
    public static <E> SubLister<E> of(E... elements) {
        return new SubLister<E>(Arrays.<E>asList(elements));
    }

    public static <E> SubLister<E> copyOf(Iterable<? extends E> elements) {
        if (elements instanceof Collection<? extends E> c) {
            return new SubLister<>(c);
        } else {
            SubLister<E> subLister = new SubLister<E>();
            for (E element : elements) {
                subLister.add(element);
            }
            return subLister;
        }
    }
}
