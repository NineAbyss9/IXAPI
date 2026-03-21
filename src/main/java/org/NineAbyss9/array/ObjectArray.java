
package org.NineAbyss9.array;

import org.NineAbyss9.util.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**A util like an array.
 *
 * @author NineAbyss*/
public class ObjectArray<E>
implements Iterable<E>, IXUtilUser //, java.io.Serializable
{
    //@java.io.Serial
    //private static final long serialVersionUID = -5024744406711451476L;
    public static final Object[] EMPTY_ARRAY = new Object[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    protected final int size;
    protected final E[] array;

    @SafeVarargs
    protected ObjectArray(E... pEs)
    {
        this(Integer.MAX_VALUE, pEs);
    }

    /**
     * Creates a new {@linkplain ObjectArray}
     */
    @SafeVarargs
    protected ObjectArray(int pSize, E... pElements)
    {
        this.size = pSize;
        this.array = pElements;
    }

    /**
     * @return true if a {@linkplain ObjectArray} is Empty.
     */
    public boolean isEmpty()
    {
        return this.array.length == 0;
    }

    /**@return the array.*/
    public E[] array()
    {
        return this.array;
    }

    public E get(int pIndex)
    {
        return this.array[pIndex];
    }

    public void set(int pIndex, E pElement)
    {
        this.array[pIndex] = pElement;
    }

    public E first()
    {
        return this.array[0];
    }

    public E last()
    {
        return this.array[this.array.length - 1];
    }

    /**
     * @return The length of a {@linkplain ObjectArray}
     */
    public int length()
    {
        return Math.min(size, this.array.length);
    }

    public int size()
    {
        return this.size;
    }

    /**Converts a element.
     *
     * @param <V> the target value type.
     */
    public <V> V convert(int pIndex) throws ClassCastException
    {
        System.out.println();
        return IXUtil.c.convert(this.get(pIndex));
    }

    public Stream<E> stream()
    {
        return Stream.of(array);
    }

    public Map<Integer, E> hashmap()
    {
        Map<Integer, E> map = new HashMap<>();
        for (int i = 0;i < length();i++)
            map.put(i, this.get(i));
        return map;
    }

    public Map<Integer, E> map()
    {
        Map<Integer, E> map = new TreeMap<>();
        for (int i = 0;i < length();i++)
            map.put(i, this.get(i));
        return map;
    }

    public void foreach(Consumer<? super E> action)
    {
        for (E element : this) {
            action.accept(element);
        }
    }

    public <T> T map(Function<E, T> func, int index)
    {
        return func.apply(array[index]);
    }

    public Iterator<E> iterator()
    {
        return stream().iterator();
    }

    public List<E> asList()
    {
        return Arrays.asList(array);
    }

    public static <E> ObjectArray<E> empty()
    {
        return new ObjectArray<>();
    }

    @SafeVarargs
    public static <T> ObjectArray<T> of(T... pArray)
    {
        return new ObjectArray<>(pArray);
    }

    @SafeVarargs
    public static <T> ObjectArray<T> withSize(int pSize, T... pElements)
    {
        return new ObjectArray<>(pSize, pElements);
    }

    public static <T> T[] emptyArray() {
        return IXUtil.c.convert(EMPTY_ARRAY);
    }
}
