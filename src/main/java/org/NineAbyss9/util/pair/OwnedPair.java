
package org.NineAbyss9.util.pair;

public final class OwnedPair<L, R>
extends MutablePair<L, R>
{
    @java.io.Serial
    private static final long serialVersionUID = 2390021132818401492L;
    private static final StackWalker TRACER;
    private final L left;
    private final R right;
    private final Class<?> owner;
    OwnedPair(L leftIn, R rightIn, Class<?> ownerIn)
    {
        left = leftIn;
        right = rightIn;
        owner = ownerIn;
    }

    public L setLeft(L key)
    {
        if (TRACER.getCallerClass() != owner) {
            return null;
        }
        return super.setLeft(key);
    }

    public R setRight(R value)
    {
        if (TRACER.getCallerClass() != owner) {
            return null;
        }
        return super.setRight(value);
    }

    static {
        TRACER = StackWalker.getInstance();
    }
}
