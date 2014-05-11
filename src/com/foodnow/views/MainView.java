package com.foodnow.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.foodnow.R;
import com.foodnow.events.Event;
import com.foodnow.events.EventListener;
import com.foodnow.models.HtmlModel;

/**
 * @author Donagh Hatton
 *         created on 3/26/14.
 */
public class MainView extends LinearLayout {
    private HtmlModel model;
    private ViewListener viewListener;

    public static interface ViewListener {
        public void onRequestEvents();
        public void onManageAccounts();
    }

    private final EventListener htmlUpdatedListener = new EventListener() {
        @Override
        public void onEvent(Event event) {
            bind();
        }
    };

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void destroy() {
        // TODO: remove listener from the model
    }

    public void setViewListener(ViewListener listener) {
        viewListener = listener;
    }

    public void setModel(HtmlModel model) {
        assert(model != null);
        if (this.model != null) {
            this.model.removeListener(HtmlModel.HtmlEvent.HTML_CHANGED, htmlUpdatedListener);
        }
        model.addListener(HtmlModel.HtmlEvent.HTML_CHANGED, htmlUpdatedListener);
        this.model = model;
    }

    private void bind() {
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Button buttonEvents = (Button) findViewById(R.id.buttonEvents);
        Button buttonAccounts = (Button) findViewById(R.id.buttonAccounts);

        buttonEvents.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                assert (viewListener != null);
                viewListener.onRequestEvents();
            }
        });

        buttonAccounts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                assert (viewListener != null);
                viewListener.onManageAccounts();
            }
        });
    }
}
