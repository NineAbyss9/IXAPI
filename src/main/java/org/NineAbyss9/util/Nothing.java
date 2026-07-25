
package org.NineAbyss9.util;

/** Class for temporary placeholder, for example:
 * <blockquote><pre>
 *     var obj = get();
 *     if (obj != null) {
 *         Nothing.getInstance().nothing();
 *     }
 * </pre></blockquote>*/
public class Nothing {
    private static Nothing instance;
    private Nothing() {
    }

    public void noting() {
    }

    public static void setInstance(Nothing newInstance) {
        instance = newInstance;
    }

    public static Nothing getInstance() {
        return instance;
    }

    static {
        instance = new Nothing();
    }
}
