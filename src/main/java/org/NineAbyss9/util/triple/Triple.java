
package org.NineAbyss9.util.triple;

import org.NineAbyss9.util.IXUtil;
import org.NineAbyss9.util.Option;

import java.util.Map;
import java.util.Objects;

public abstract class Triple<L, M, R>
implements Map.Entry<L, R>, Comparable<Triple<L, M, R>>, java.io.Serializable, org.NineAbyss9.util.IXUtilUser {
    @java.io.Serial
    private static final long serialVersionUID = 8947688449640458794L;
    Triple() {
    }

    public abstract L left();

    public abstract M middle();

    public abstract R right();

    public L getKey() {
        return left();
    }

    public R getValue() {
        return right();
    }

    public Triple<R, M, L> swap() {
        return of(right(), middle(), left());
    }

    public <T, O, I> Triple<T, O, I> cast() {
        return IXUtil.c.convert(this);
    }

    public <T> T castLeft() {
        return IXUtil.c.convert(this.left());
    }

    public <T> T castMiddle()
    {
        return IXUtil.c.convert(this.middle());
    }

    public <T> T castRight() {
        return IXUtil.c.convert(this.right());
    }

    public Triple<L, M, R> mutable() {
        return mutable(left(), middle(), right());
    }

    public Option<L> leftOption() {
        return Option.ofNullable(left());
    }

    public Option<M> middleOption()
    {
        return Option.ofNullable(middle());
    }

    public Option<R> rightOption() {
        return Option.ofNullable(right());
    }

    public R setValue(R value) {
        return this.setRight(value);
    }

    public L setKey(L key) {
        return this.setLeft(key);
    }

    public void set(L left, M middle, R right) {
        this.setLeft(left);
        this.setMiddle(middle);
        this.setRight(right);
    }

    public Triple<L, M, R> copy()
    {
        return isMutable() ? MutableTriple.of(left(), middle(), right()) : ImmutableTriple.of(left(), middle(), right());
    }

    public boolean isMutable()
    {
        return false;
    }

    public abstract L setLeft(L left);

    public abstract M setMiddle(M middle);

    public abstract R setRight(R right);

    public int getLInt() {
        return (int)this.left();
    }

    public int getRInt() {
        return (int)this.right();
    }

    public double getLDouble() {
        return (double)this.left();
    }

    public double getRDouble() {
        return (double)this.right();
    }

    public float getLFloat() {
        return (float)this.left();
    }

    public float getRFloat() {
        return (float)this.right();
    }

    public int getMInt() {
        return (int)this.middle();
    }

    public double getMDouble() {
        return (double)this.middle();
    }

    public float getMFloat() {
        return (float)this.middle();
    }

    public int compareTo(Triple<L, M, R> o) {
        boolean leftEquals = Objects.equals(left(), o.left());
        boolean middleEquals = Objects.equals(middle(), o.middle());
        boolean rightEquals = Objects.equals(right(), o.right());
        return leftEquals && middleEquals && rightEquals ? 0 : leftEquals ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Triple<?,?,?> triple)
            return Objects.equals(triple.left(), left()) && Objects.equals(triple.middle(), middle()) &&
                    Objects.equals(triple.right(), right());
        if (obj instanceof Map.Entry<?,?> entry)
            return Objects.equals(entry.getKey(), left()) && Objects.equals(entry.getValue(), right());
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(left()) ^ Objects.hashCode(middle()) ^ Objects.hashCode(right());
    }

    public String toString() {
        return "Triple{" + "left:" + this.left() + ",middle:" + middle() +  ",right:" + this.right() + "}";
    }

    public static <L, M, R> Triple<L, M, R> mutable(L left, M middle, R right) {
        return MutableTriple.of(left, middle, right);
    }

    public static <K, M, V> Triple<K, M, V> empty() {
        return new EmptyTriple<>();
    }

    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return ImmutableTriple.of(left, middle, right);
    }

    public static <L, M, R> Triple<L, M, R> of(Map.Entry<L, R> entry) {
        return ImmutableTriple.of(entry);
    }
}
