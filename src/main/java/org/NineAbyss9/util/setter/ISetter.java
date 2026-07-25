
package org.NineAbyss9.util.setter;

import org.NineAbyss9.value_holder.BooleanValueHolder;

import java.util.Set;

public interface ISetter<E>
extends Set<E> {
    BooleanValueHolder<E> addValue(E pValue);

    default boolean addIfAbsent(E pValue)
    {
        if (this.contains(pValue)) return false;
        return this.add(pValue);
    }
}
