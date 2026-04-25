
package org.NineAbyss9.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**A simple event container*/
public class EventContainer {
    private static final Map<Class<? extends Event>, List<EventListener>> listeners = new HashMap<>();
    public EventContainer() {
    }

    public static <T extends Event> void register(Class<T> eventClass, EventListener listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
    }

    public static <T extends Event> void post(T event) {
        var list = listeners.get(event.getClass());
        if (list != null) {
            for (var consumer : list) {
                consumer.handleEvent(event);
            }
        }
    }

    public static void unregister(Class<? extends Event> eventClass, EventListener listener) {
        var list = listeners.get(eventClass);
        if (list != null) {
            list.remove(listener);
        }
    }
}
