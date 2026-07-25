
package org.NineAbyss9.util.lister;

import javax.annotation.concurrent.Immutable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Elements<E>
{
    private static final Map<Iterable<?>, Boolean> CHECKED_MAP;
    private final Iterable<E> iterable;
    private final boolean mutable;
    private Elements(final Iterable<E> it, final boolean mutableIn)
    {
        this.iterable = it;
        this.mutable = mutableIn;
    }

    public boolean are(E e)
    {
        if (!iterable.iterator().hasNext()) return true;
        if (mutable) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (equals(it.next(), e))
                {
                    continue;
                }
                return false;
            }
            return true;
        }
        if (CHECKED_MAP.containsKey(iterable))
        {
            return CHECKED_MAP.get(iterable);
        }
        boolean flag = true;
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            if (equals(it.next(), e)) {
                continue;
            }
            flag = false;
            break;
        }
        CHECKED_MAP.put(iterable, flag);
        return flag;
    }

    public boolean areNull()
    {
        return are(null);
    }

    private boolean equals(E a, E b)
    {
        if (a == b) return true;
        if (a == null || b == null) return false;
        return a.equals(b);
    }

    public static <T> Elements<T> autoCheck(final Iterable<T> it)
    {
        String name = it.getClass().getSimpleName();
        boolean immutable = name.contains("Immutable")
                || name.contains("Unmodifiable") || it.getClass().isAnnotationPresent(Immutable.class);
        return new Elements<>(it, !immutable);
    }

    /**@param it an immutable iterable, such as a list created by {@linkplain java.util.List#of(Object[])}*/
    public static <T> Elements<T> of(final Iterable<T> it)
    {
        return new Elements<>(it, false);
    }

    /**@param it a mutable iterable, such as an {@linkplain java.util.ArrayList}*/
    public static <T> Elements<T> mutable(final Iterable<T> it)
    {
        return new Elements<>(it, true);
    }

    static {
        CHECKED_MAP = new ConcurrentHashMap<>(1145);
    }
}
