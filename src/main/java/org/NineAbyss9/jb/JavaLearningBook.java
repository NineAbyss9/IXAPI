
package org.NineAbyss9.jb;

import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unused")
public class JavaLearningBook
{
    public static void initialize(Class<?> clazz, String skills)
    {
    }

    static {
        initialize(ConcurrentHashMap.class, "the \"put\" method requires both non-null key and value.");
        initialize(Thread.class, "start() method starts a thread in a new thread of execution.");
    }
}
