
package org.NineAbyss9.util;

import org.NineAbyss9.util.function.FunctionCollector;

public class Action
{
    private final Runnable action;
    private final Runnable action1;
    public Action(final Runnable trueAction, final Runnable falseAction) {
        this.action = trueAction;
        this.action1 = falseAction;
    }

    public void run(final boolean flag) {
        if (flag)
            action.run();
        else
            action1.run();
    }

    /**@param trueAction runs if the {@code flag} is true.
     *
     * @param flag the flag.*/
    public static void run(Runnable trueAction, Runnable falseAction, boolean flag)
    {
        (flag ? trueAction : falseAction).run();
    }

    public static void run(Runnable trueAction, boolean flag)
    {
        if (flag) {
            trueAction.run();
        }
    }

    public static void runFalse(Runnable falseAction, boolean flag)
    {
        if (!flag) {
            falseAction.run();
        }
    }

    public static Action emptyFalse(final Runnable trueAction) {
        return new Action(trueAction, FunctionCollector.emptyAction());
    }

    public static Action emptyTrue(final Runnable falseAction) {
        return new Action(FunctionCollector.EMPTY_ACTION, falseAction);
    }
}
