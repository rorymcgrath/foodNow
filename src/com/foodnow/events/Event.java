package com.foodnow.events;

import net.jcip.annotations.Immutable;

/**
 * @author Donagh Hatton
 *         created on 3/27/14.
 */
@Immutable
public class Event {
    private final String type;
    private final Dispatcher sender;

    public Event(String type, Dispatcher sender) {
        this.type = type;
        this.sender = sender;
    }

    public Dispatcher getSender() {
        return sender;
    }

    public String getType() {
        return type;
    }
}
