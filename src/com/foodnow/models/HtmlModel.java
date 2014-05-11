package com.foodnow.models;

import com.foodnow.events.Dispatcher;
import com.foodnow.events.Event;
import com.foodnow.events.EventDispatcher;

/**
 * @author Donagh Hatton
 * created on 3/26/14.
 */
public class HtmlModel extends EventDispatcher {
    public static class HtmlEvent extends Event {
        public static final String HTML_CHANGED = "HTML_CHANGED";

        public HtmlEvent(String type, Dispatcher sender) {
            super(type, sender);
        }
    }

    private String html = "";

    public String getHtml() {
        return html;
    }

    public void updateHtml(String html) {
        this.html = html;
        dispatch(new HtmlEvent(HtmlEvent.HTML_CHANGED, this));
    }
}
