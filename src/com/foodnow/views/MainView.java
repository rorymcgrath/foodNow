package com.foodnow.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.foodnow.R;

/**
 * @author Donagh Hatton
 *         created on 3/26/14.
 */
public class MainView extends LinearLayout {
    private ViewListener viewListener;

    public static interface ViewListener {
        public void onEventOne();
        public void onEventTwo();
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void destroy() {
        // TODO: remove listener from the model
    }

    public void setViewListener(ViewListener listener) {
        viewListener = listener;
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
                viewListener.onEventOne();
            }
        });

        buttonAccounts.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                assert (viewListener != null);
                viewListener.onEventTwo();
            }
        });
    }
}
