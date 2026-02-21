
package org.NineAbyss9.array;

public class IntArray
extends NumberArray<Integer> {
    public IntArray(Integer... pTs) {
        super(pTs);
    }

    public static IntArray of(Integer... pIntegers) {
        return new IntArray(pIntegers);
    }
}
