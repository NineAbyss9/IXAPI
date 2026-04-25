
package org.NineAbyss9.util.pair;

import org.NineAbyss9.util.IXUtil;
import org.NineAbyss9.util.Option;

import java.util.Map;
import java.util.Objects;

public abstract class Pair<L, R>
implements Map.Entry<L, R>, Comparable<Pair<L, R>>, java.io.Serializable, org.NineAbyss9.util.IXUtilUser {
    @java.io.Serial
    private static final long serialVersionUID = 8947688449640458794L;
    Pair() {
    }

    public abstract L left();

    public abstract R right();

    public L getKey() {
        return left();
    }

    public R getValue() {
        return right();
    }

    public Pair<R, L> swap() {
        return of(right(), left());
    }

    public <T, I> Pair<T, I> cast() {
        return IXUtil.c.convert(this);
    }

    public <T> T castLeft() {
        return IXUtil.c.convert(this.left());
    }

    public <T> T castRight() {
        return IXUtil.c.convert(this.right());
    }

    public Pair<L, R> mutable() {
        return mutable(left(), right());
    }

    public Option<L> leftOption() {
        return Option.ofNullable(left());
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

    public void set(L left, R right) {
        this.setLeft(left);
        this.setRight(right);
    }

    public abstract L setLeft(L left);

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

    public int compareTo(Pair<L, R> o) {
        boolean leftEquals = Objects.equals(left(), o.left());
        boolean rightEquals = Objects.equals(right(), o.right());
        return leftEquals && rightEquals ? 0 : leftEquals ? 1 : -1;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Map.Entry<?,?> entry)
            return Objects.equals(entry.getKey(), getKey()) && Objects.equals(entry.getValue(), getValue());
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    public String toString() {
        return "Pair{" + "left:" + this.left() + ", right:" + this.right() + "}";
    }

    public static <L, R> Pair<L, R> mutable(L left, R right) {
        return MutablePair.of(left, right);
    }

    public static <K, V> Pair<K, V> empty() {
        return new EmptyPair<>();
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return ImmutablePair.of(left, right);
    }

    public static <L, R> Pair<L, R> of(Map.Entry<L, R> entry) {
        return ImmutablePair.of(entry);
    }
}
