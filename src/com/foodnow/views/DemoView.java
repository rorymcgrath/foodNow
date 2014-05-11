package com.foodnow.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.foodnow.R;
import com.foodnow.events.Event;
import com.foodnow.events.EventListener;
import com.foodnow.models.DemoModel;

/**
 * @author Donagh Hatton
 *         created on 3/26/14.
 */
public class DemoView extends LinearLayout {
    private DemoModel model;
    private ViewListener viewListener;
    private ProgressDialog dialog = new ProgressDialog(getContext());

    public static interface ViewListener {
        public void onTextClicked();
    }

    private final EventListener modelUpdatedListener = new EventListener() {
        @Override
        public void onEvent(Event event) {
            bind();
            dialog.dismiss();
        }
    };

    private final EventListener modelUpdatingListener = new EventListener() {
        @Override
        public void onEvent(Event event) {
            dialog.setMessage("Fetching...");
            dialog.show();
        }
    };

    public DemoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void destroy() {
        // TODO: remove listener from the model, this needs to go in
    }

    public void setViewListener(ViewListener listener) {
        viewListener = listener;
    }

    public void setModel(DemoModel model) {
        assert(model != null);
        if (this.model != null) {
            this.model.removeListener(DemoModel.DemoEvent.DEMO_CHANGED, modelUpdatedListener);
            this.model.removeListener(DemoModel.DemoEvent.DEMO_UPDATING_BEGIN, modelUpdatingListener);
        }
        model.addListener(DemoModel.DemoEvent.DEMO_CHANGED, modelUpdatedListener);
        model.addListener(DemoModel.DemoEvent.DEMO_UPDATING_BEGIN, modelUpdatingListener);
        this.model = model;
    }

    private void bind() {
        ((TextView) findViewById(R.id.textView)).setText(model.getData());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                assert viewListener != null;
                viewListener.onTextClicked();
            }
        });

    }
}
