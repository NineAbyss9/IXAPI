
package org.NineAbyss9.util.triple;

public final class OwnedTriple<L, M, R>
extends MutableTriple<L, M, R>
{
    @java.io.Serial
    private static final long serialVersionUID = 2390021132818401492L;
    private static final StackWalker TRACER;
    private final Class<?> owner;
    OwnedTriple(Class<?> ownerIn)
    {
        owner = ownerIn;
    }

    public L setLeft(L key)
    {
        if (TRACER.getCallerClass() != owner) {
            return null;
        }
        return super.setLeft(key);
    }

    public M setMiddle(M middle)
    {
        if (TRACER.getCallerClass() != owner) {
            return null;
        }
        return super.setMiddle(middle);
    }

    public R setRight(R value)
    {
        if (TRACER.getCallerClass() != owner) {
            return null;
        }
        return super.setRight(value);
    }

    public Triple<L, M, R> mutable()
    {
        return this;
    }

    static {
        TRACER = StackWalker.getInstance();
    }
}
