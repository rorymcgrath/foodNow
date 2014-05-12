package com.foodNow.models;

import com.foodNow.events.Dispatcher;
import com.foodNow.events.Event;
import com.foodNow.events.EventDispatcher;

/**
 * @author Donagh Hatton
 * created on 3/26/14.
 */
public class DemoModel extends EventDispatcher implements Model {
    private String data = "";

    public String getData() {
        return data;
    }

    public DemoModel setData(String data) {
        this.data = data;
        return this;
    }

    public DemoModel setUpdating(boolean isUpdating) {
        if (isUpdating) {
            dispatch(new DemoEvent(DemoEvent.DEMO_UPDATING_BEGIN, this));
        } else {
            dispatch(new DemoEvent(DemoEvent.DEMO_UPDATING_END, this));
        }
        return this;
    }

    @Override
    public void onUpdated() {
        setUpdating(false);
        dispatch(new DemoEvent(DemoEvent.DEMO_CHANGED, this));
    }

    public static class DemoEvent extends Event {
        public static final String DEMO_CHANGED = "DEMO_CHANGED";
        public static final String DEMO_UPDATING_BEGIN = "DEMO_UPDATING_BEGIN";
        public static final String DEMO_UPDATING_END = "DEMO_UPDATING_END";

        public DemoEvent(String type, Dispatcher sender) {
            super(type, sender);
        }
    }
}
