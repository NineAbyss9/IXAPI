
package org.NineAbyss9.util.function;

import java.util.function.ToDoubleFunction;

@FunctionalInterface
public interface DoubleToDoubleFunction
extends ToDoubleFunction<Double>
{
    @Deprecated
    default double applyAsDouble(Double value)
    {
        return applyAsDouble(value.doubleValue());
    }

    double applyAsDouble(double value);
}
