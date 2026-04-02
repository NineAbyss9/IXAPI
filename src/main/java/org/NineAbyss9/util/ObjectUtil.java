
package org.NineAbyss9.util;

public class ObjectUtil {
    private ObjectUtil() {
    }

    public static <T, R extends T> T orElse(T t1, R t2) {
        return t1 == null ? t2 : t1;
    }
}
