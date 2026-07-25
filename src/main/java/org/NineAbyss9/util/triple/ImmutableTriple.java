
package org.NineAbyss9.util.triple;

import org.NineAbyss9.util.IXUtil;

public final class ImmutableTriple<L, M, R> extends Triple<L, M, R>
{
    @java.io.Serial
    private static final long serialVersionUID = 4641014950638825776L;
    private final L left;
    private final M middle;
    private final R right;
    public ImmutableTriple(final L pLeft, final M middleIn, final R pRight) {
        left = pLeft;
        middle = middleIn;
        right = pRight;
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

    public R setRight(R value) {
        return IXUtil.newUnsupportedOperation();
    }

    public M setMiddle(M middle)
    {
        return IXUtil.newUnsupportedOperation();
    }

    public L setLeft(L key) {
        return IXUtil.newUnsupportedOperation();
    }

    public static <L, M, R> ImmutableTriple<L, M, R> of(final L left, final M middle, final R right) {
        return new ImmutableTriple<>(left, middle, right);
    }

    public static <L, M, R> ImmutableTriple<L, M, R> of(final java.util.Map.Entry<L, R> entry) {
        if (entry == null)
            return new ImmutableTriple<>((L)null, (M)null, (R)null);
        return new ImmutableTriple<>(entry.getKey(), (M)null, entry.getValue());
    }
}
