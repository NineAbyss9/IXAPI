
package org.NineAbyss9.util.function;

import java.util.ArrayDeque;
import java.util.Deque;

public class JoinableRunnable
implements Runnable
{
    private final Deque<Runnable> queue;
    private JoinableRunnable(int queueSize, Runnable mainAction)
    {
        this.queue = new ArrayDeque<Runnable>(queueSize);
        this.queue.add(mainAction);
    }

    public JoinableRunnable join(Runnable runnable, boolean first)
    {
        if (first)
        {
            this.queue.addFirst(runnable);
            return this;
        }
        this.queue.add(runnable);
        return this;
    }

    /**It equals to {@linkplain JoinableRunnable#join(Runnable, boolean) join(runnable, false)}*/
    public JoinableRunnable join(Runnable runnable)
    {
        this.queue.add(runnable);
        return this;
    }

    public void run()
    {
        for (Runnable runnable : this.queue)
        {
            runnable.run();
        }
    }

    public static JoinableRunnable create(int capacity, Runnable mainAction)
    {
        return new JoinableRunnable(capacity, mainAction);
    }

    /**Creates a new JoinableRunnable with default capacity 16 and no main action*/
    public static JoinableRunnable create()
    {
        return new JoinableRunnable(16, FunctionCollector.emptyAction());
    }
}
