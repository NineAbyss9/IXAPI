
package org.NineAbyss9.math;

import org.NineAbyss9.cache.Cache;
import org.NineAbyss9.util.function.DoubleToDoubleFunction;

import java.util.function.DoubleToLongFunction;

public enum Unit
{
    /// Kilo
    K(value -> value * 1000.0D),
    /// Million
    M(value -> {
        value = K.toDouble(value);
        value = K.toDouble(value);
        return value;
    }),
    /// Billion
    B(value -> {
        for (int i = 0;i < 3;i++)
            value = value * 1000.0D;
        return value;
    }),
    ///"value" is tick (in minecraft)
    SECOND(value -> value * 20.0D),
    ///"value" is second
    MINUTE(value -> value * 60.0D),
    KILOMETER(K::toDouble),
    ;
    public static final Unit[] VALUES = values();
    private final int index;
    private final DoubleToDoubleFunction doubleFunction;
    private final DoubleToLongFunction doubleToLongFunction;
    static {Cache.putIfAbsent(0, -1);}
    Unit(DoubleToDoubleFunction func)
    {
        this.doubleFunction = func;
        this.doubleToLongFunction = value -> (long)func.applyAsDouble((double)value);
        index = Cache.put(0, (int)(Cache.get(0)) + 1);
    }

    public double toDouble(double value)
    {
        return this.doubleFunction.applyAsDouble(value);
    }

    public int toInt(int value)
    {
        return Math.toIntExact(doubleToLongFunction.applyAsLong(value));
    }

    public float toFloat(float value)
    {
        return (float)doubleFunction.applyAsDouble((double)value);
    }

    public long toLong(long value)
    {
        return doubleToLongFunction.applyAsLong(value);
    }

    public int getIndex()
    {
        return index;
    }

    public static Unit getByIndex(int index)
    {
        return VALUES[index];
    }
}
