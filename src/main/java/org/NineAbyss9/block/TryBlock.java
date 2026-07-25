
package org.NineAbyss9.block;

public class TryBlock {
    private final Runnable runnable;
    public TryBlock(Runnable work) {
        this.runnable = work;
    }

    public boolean run() {
        try {
            runnable.run();
        } catch (Exception ignore) {
            return false;
        }
        return true;
    }
}
