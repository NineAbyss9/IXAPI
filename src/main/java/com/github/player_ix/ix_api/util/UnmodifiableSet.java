
package com.github.player_ix.ix_api.util;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**A unmodifiable {@linkplain Set}
 * @see UnmodifiableList
 * @author Player_IX*/
public final class UnmodifiableSet<T>
extends HashSet<T>
implements Set<T> {
    final transient Object[] array;
    @SafeVarargs
    private UnmodifiableSet(T... ts) {
        array = ts;
    }

    @Nonnull
    @SafeVarargs
    public static <T> UnmodifiableSet<T> of(T... ts) {
        return new UnmodifiableSet<>(ts);
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T)array[index];
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }
}
