
package org.NineAbyss9.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@SuppressWarnings("unchecked")
public class Cache {
    /**For {@linkplain org.NineAbyss9.math.Unit}*/
    public static final int UNIT = 0;
    private final Map<Integer, Object> caches = new LinkedHashMap<>();
    private static int next;
    private static Cache instance;
    public Cache() {
        instance = this;
        caches.put(0, -1);
    }

    public static void clearCache() {
        instance = null;
    }

    public static void clear() {
        instance.caches.clear();
    }

    public static Cache getInstance() {
        return instance;
    }

    static int next() {
        return next++;
    }

    public static <T> T get(int key) {
        return (T)instance.caches.get(key);
    }

    public static <T> T computeIfAbsent(int key, Function<Integer, ?> func) {
        return (T)instance.caches.computeIfAbsent(key, func);
    }

    public static <T> T compute(int key, BiFunction<? super Integer, ? super Object, ? extends T> remappingFunction) {
        return (T)instance.caches.compute(key, remappingFunction);
    }

    public static <T> T putIfAbsent(int key, T value) {
        return (T)instance.caches.putIfAbsent(key, value);
    }

    public static <T> T put(int key, Object value) {
        return (T)instance.caches.put(key, value);
    }

    public static <T> T add(Object value) {
        return (T)instance.caches.put(next(), value);
    }

    public static <T> T remove(Integer key) {
        return (T)instance.caches.remove(key);
    }
}
