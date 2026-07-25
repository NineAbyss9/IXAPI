
package org.NineAbyss9.util;

public final class Converter {
    Converter() {
    }

    /**@throws ClassCastException if can't {@linkplain Class#cast(Object) cast} {@code a}*/
    @SuppressWarnings("unchecked")
    public <T> T convert(Object a) {
        return (T)a;
    }

    /**@throws ClassCastException if can't {@linkplain Class#cast(Object) cast} {@code a}*/
    public <T> T convert(Object obj, Class<T> clazz) {
        return clazz.cast(obj);
    }
}
