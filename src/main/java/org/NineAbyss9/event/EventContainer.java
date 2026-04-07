
package org.NineAbyss9.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**A simple event container*/
public class EventContainer {
    private static final Map<Class<? extends Event>, List<Consumer<Event>>> listeners = new HashMap<>();
    private static final List<EventListener> genericListeners = new ArrayList<>();
    public EventContainer() {
    }

    public static <T extends Event> void register(Class<T> eventClass, Consumer<T> listener) {
        listeners.computeIfAbsent(eventClass, k -> new ArrayList<>()).add((Consumer<Event>) listener);
    }

    public static void post(Event event) {
        List<Consumer<Event>> list = listeners.get(event.getClass());
        if (list != null) {
            for (Consumer<Event> consumer : list) {
                consumer.accept(event);
            }
        }
        for (EventListener listener : genericListeners) {
            listener.handleEvent(event);
        }
    }

    public static void registerListener(EventListener listener) {
        genericListeners.add(listener);
    }

    public static void unregister(Class<? extends Event> eventClass, Consumer<Event> listener) {
        List<Consumer<Event>> list = listeners.get(eventClass);
        if (list != null) {
            list.remove(listener);
        }
    }

    public static void unregisterListener(EventListener listener) {
        genericListeners.remove(listener);
    }
}
