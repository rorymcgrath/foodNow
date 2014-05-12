package com.foodNow.events;

/**
 * @author Donagh Hatton
 *         created on 3/27/14.
 */
public interface Dispatcher {
    public abstract Dispatcher addListener(String type, EventListener listener);

    public abstract Dispatcher removeListener(String type, EventListener listener);

    public abstract void dispatch(Event event);
}
