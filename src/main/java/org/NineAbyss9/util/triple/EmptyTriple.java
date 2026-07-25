
package org.NineAbyss9.util.triple;

final class EmptyTriple<L, M, R> extends Triple<L, M, R>
{
    @java.io.Serial
    private static final long serialVersionUID = 1L;

    public L left() {
        return null;
    }

    public M middle()
    {
        return null;
    }

    public Triple<L, M, R> mutable()
    {
        return Triple.mutable((L)null, (M)null, (R)null);
    }

    public R right() {
        return null;
    }

    public R setRight(R value) {
        return null;
    }

    public M setMiddle(M middle)
    {
        return null;
    }

    public L setLeft(L key) {
        return null;
    }
}
