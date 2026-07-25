
package org.NineAbyss9.util.triple;

public class MutableTriple<L, M, R>
extends Triple<L, M, R>
{
    @java.io.Serial
    private static final long serialVersionUID = 848409944736507155L;
    private L left;
    private M middle;
    private R right;
    MutableTriple() {
        left = null;
        middle = null;
        right = null;
    }

    MutableTriple(L l, M m, R r) {
        left = l;
        middle = m;
        right = r;
    }

    public boolean isMutable()
    {
        return true;
    }

    public L left() {
        return left;
    }

    public M middle()
    {
        return middle;
    }

    public R right() {
        return right;
    }

    public L setLeft(L key) {
        return left = key;
    }

    public M setMiddle(M middle)
    {
        return this.middle = middle;
    }

    public R setRight(R value) {
        return right = value;
    }

    public static <L, M, R> MutableTriple<L, M, R> of() {
        return new MutableTriple<>();
    }

    public static <L, M, R> MutableTriple<L, M, R> of(L left, M middle, R right) {
        return new MutableTriple<>(left, middle, right);
    }
}
