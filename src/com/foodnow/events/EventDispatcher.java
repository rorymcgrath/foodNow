package com.foodnow.events;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Donagh Hatton
 *         created on 3/27/14.
 */
public class EventDispatcher implements Dispatcher {
    private final Map<String, CopyOnWriteArrayList<EventListener>> listenerMap;

    public EventDispatcher() {
        listenerMap = new HashMap<String, CopyOnWriteArrayList<EventListener>>();
    }

    @Override
    public Dispatcher addListener(String type, EventListener listener) {
        synchronized (listenerMap) {
            if (!listenerMap.containsKey(type)) {
                listenerMap.put(type, new CopyOnWriteArrayList<EventListener>());
            }
            listenerMap.get(type).add(listener);
        }
        return this;
    }

    @Override
    public Dispatcher removeListener(String type, EventListener listener) {
        synchronized (listenerMap) {
            if (listenerMap.containsKey(type)) {
                listenerMap.get(type).remove(listener);
            }
        }
        return this;
    }

    @Override
    public void dispatch(Event event) {
        assert(event != null);
        String eventType = event.getType();
        synchronized (listenerMap) {
            if (listenerMap.containsKey(eventType)) {
                for (EventListener listener : listenerMap.get(eventType)) {
                    listener.onEvent(event);
                }
            }
        }
    }
}
