
package org.NineAbyss9.util;

public final class LazyValue<T>
{
    private T value = null;
    private boolean initialized;
    public LazyValue() {
    }

    public T get()
    {
        return value;
    }

    /**Sets the value of this {@linkplain LazyValue} and marks it as initialized,unless it has already been initialized.
     *
     * @return self*/
    public LazyValue<T> set(T valueIn)
    {
        if (initialized) return this;
        this.value = valueIn;
        this.initialized = true;
        return this;
    }

    public static <T> LazyValue<T> newInstance()
    {
        return new LazyValue<T>();
    }
}
