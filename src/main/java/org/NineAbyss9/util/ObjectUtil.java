
package org.NineAbyss9.util;

public class ObjectUtil {
    private ObjectUtil() {
    }

    public static boolean nonnullEquals(Object obj, Object o) {
        return obj != null && obj.equals(o);
    }

    public static <T, R extends T> T orElse(T t1, R t2) {
        return t1 == null ? t2 : t1;
    }
}
