
package org.NineAbyss9.util;

import java.io.IOException;
import java.util.logging.Logger;

public final class IXUtil {
    final Object obj;
    public static final Converter c;
    public static final Logger l;
    public final Manager m;
    public static Nothing n;
    IXUtil(Object o) {
        obj = o;
        m = new Manager(o.getClass());
    }

    ///  this method make you create an array more convenient.
    ///  @return the array
    @SafeVarargs
    public static <T> T[] make(T... t) {
        return t;
    }

    public static void newIO(String message) throws IOException {
        throw new IOException(message);
    }

    public static void newRuntime() {throw new RuntimeException();}

    public static void newRuntime(String mes) {
        throw new RuntimeException(mes);
    }

    public static void newRuntime(Exception e) {throw new RuntimeException(e);}

    static {
        c = new Converter();
        l = Logger.getLogger("IXUtil");
        n = Nothing.getInstance();
    }
}
