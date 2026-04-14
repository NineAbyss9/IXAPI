
package org.NineAbyss9.util.map;

import org.NineAbyss9.util.pair.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public final class Maps
{
    public static <K, V> HashMap<K, V> hashmap(K[] k, V[] v)
    {
        var map = new HashMap<K, V>();
        for (int i = 0;i< k.length;i++) {
            map.put(k[i], v[i]);
        }
        return map;
    }

    public static <K, V> HashMap<K, V> hashmap(List<Pair<K, V>> list)
    {
        var map = new HashMap<K, V>();
        list.forEach(kvPair -> map.put(kvPair.left(), kvPair.right()));
        return map;
    }

    public static <K extends Comparable<K>, V> TreeMap<K, V> treemap(K[] k, V[] v)
    {
        var map = new TreeMap<K, V>();
        for (int i = 0;i< k.length;i++) {
            map.put(k[i], v[i]);
        }
        return map;
    }

    public static <K extends Comparable<K>, V> TreeMap<K, V> treemap(List<Pair<K, V>> list)
    {
        var map = new TreeMap<K, V>();
        list.forEach(kvPair -> map.put(kvPair.left(), kvPair.right()));
        return map;
    }
}
