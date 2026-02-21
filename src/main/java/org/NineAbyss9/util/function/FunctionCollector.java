
package org.NineAbyss9.util.function;

import org.NineAbyss9.util.IXUtil;

import java.util.function.*;

public class FunctionCollector {
    private FunctionCollector() {
        throw new AssertionError();
    }

    public static <T> Predicate<T> alwaysTrue() {
        return PredicateInstance.ALWAYS_TRUE.convert();
    }

    public static <T> Predicate<T> alwaysFalse() {
        return PredicateInstance.ALWAYS_FALSE.convert();
    }

    public static <T> Predicate<T> notnull() {
        return PredicateInstance.NOT_NULL.convert();
    }

    public static <T> Predicate<T> isNull() {
        return PredicateInstance.IS_NULL.convert();
    }

    public static BooleanSupplier positiveSupplier() {
        return BooleanSupplierInstance.TRUE;
    }

    public static BooleanSupplier negativeSupplier() {
        return BooleanSupplierInstance.FALSE;
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

    private enum PredicateInstance implements Predicate<Object>, org.NineAbyss9.util.IXUtilUser {
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

        <T> Predicate<T> convert() {
            return IXUtil.c.convert(this);
        }
    }
}
