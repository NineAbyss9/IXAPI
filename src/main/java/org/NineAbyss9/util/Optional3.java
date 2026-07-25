
package org.NineAbyss9.util;

import org.NineAbyss9.util.function.Decomposer;
import org.NineAbyss9.util.triple.Triple;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Optional3<T, E, C>
{
    private final T t;
    private final E e;
    private final C c;
    private final boolean isPresent;
    public Optional3(T t, E e, C c)
    {
        this.t = t;
        this.e = e;
        this.c = c;
        this.isPresent = this.isPresent();
    }

    public Triple<T, E, C> get()
    {
        return Triple.of(t, e, c);
    }

    public T getT()
    {
        return t;
    }

    public E getE()
    {
        return e;
    }

    public C getC()
    {
        return c;
    }

    public boolean ifTPresent(Consumer<T> consumer)
    {
        if (t == null) return false;
        consumer.accept(t);
        return true;
    }

    public boolean ifPresent(Decomposer<T, E, C> decomposer)
    {
        if (this.isPresent) {
            decomposer.accept(t, e, c);
            return true;
        }
        return false;
    }

    public Triple<T, E, C> orElseGet(T oT, E oE, C oC)
    {
        if (this.isPresent) return Triple.of(t, e, c);
        else return Triple.of(oT, oE, oC);
    }

    public Triple<T, E, C> orElseGet(Triple<T, E, C> triple)
    {
        if (this.isPresent) return Triple.of(t, e, c);
        else return triple;
    }

    public Triple<T, E, C> orElseThrow()
    {
        if (isEmpty()) throw new NoSuchElementException();
        else return Triple.of(t, e, c);
    }

    public boolean isPresent()
    {
        if (t == null) return false;
        if (e == null) return false;
        return c != null;
    }

    public boolean isEmpty()
    {
        return !this.isPresent;
    }
}
