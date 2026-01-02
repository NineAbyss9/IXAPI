
package com.github.player_ix.ix_api.util;

import com.google.common.annotations.VisibleForTesting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;

/**A unmodifiable {@linkplain List}
 * @see UnmodifiableSet
 * @author Player_IX*/
public final class UnmodifiableList<T>
extends AbstractList<T>
implements List<T> {
    @VisibleForTesting
    final transient Object[] array;

    @SafeVarargs
    private UnmodifiableList(T... objects) {
        array = objects;
    }

    @SafeVarargs
    @Nonnull
    public static <T> UnmodifiableList<T> of(T... objects) {
        return new UnmodifiableList<>(objects);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        return (T)array[index];
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Nullable
    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return array;
    }
}
