
package org.NineAbyss9.util.function;

import org.NineAbyss9.util.*;

import javax.annotation.Nullable;
import java.util.function.*;

@SuppressWarnings("unchecked")
public class FunctionCollector {
    public static final Runnable EMPTY_ACTION = () -> {
    };
    public static final Consumer<Object> EMPTY_CONSUMER = (obj) -> {
    };

    private FunctionCollector() {
        throw new AssertionError();
    }

    /**{@linkplain Predicate}*/
    public static <T> Predicate<T> alwaysTrue() {
        return (Predicate<T>)PredicateInstance.ALWAYS_TRUE;
    }

    public static <T> Predicate<T> alwaysFalse() {
        return (Predicate<T>)PredicateInstance.ALWAYS_FALSE;
    }

    public static <T> Predicate<T> notnull() {
        return (Predicate<T>)PredicateInstance.NOT_NULL;
    }

    public static <T> Predicate<T> isNull() {
        return (Predicate<T>)PredicateInstance.IS_NULL;
    }

    /**{@linkplain Supplier}*/
    public static BooleanSupplier positiveSupplier() {
        return BooleanSupplierInstance.TRUE;
    }

    public static BooleanSupplier negativeSupplier() {
        return BooleanSupplierInstance.FALSE;
    }

    public static <T> T get(Supplier<T> supplier) {
        return supplier.get();
    }

    /// Makes a new Supplier
    public static <T> Supplier<T> supplier(T value) {
        return () -> value;
    }

    /**{@linkplain Runnable}*/
    public static Runnable emptyAction() {
        return EMPTY_ACTION;
    }

    /**{@linkplain Consumer}*/
    public static <T> Consumer<T> accept() {
        return IXUtil.c.convert(EMPTY_CONSUMER);
    }

    public static <T> void accept(@Nullable T obj, Consumer<T> action) {
        if (obj == null) return;
        action.accept(obj);
    }

    private enum BooleanSupplierInstance implements BooleanSupplier {
        TRUE(true),
        FALSE(false);
        final boolean b;
        BooleanSupplierInstance(boolean p) {
            b = p;
        }

        public boolean getAsBoolean() {
            return b;
        }
    }

    private enum PredicateInstance implements Predicate<Object>, IXUtilUser {
        ALWAYS_TRUE {
            public boolean test(Object t) {
                return true;
            }
        },
        ALWAYS_FALSE,
        NOT_NULL {
            public boolean test(Object t) {
                return t != null;
            }
        },
        IS_NULL {
            public boolean test(Object t) {
                return t == null;
            }
        };

        PredicateInstance() {}

        public boolean test(Object t) {
            return false;
        }
    }
}
