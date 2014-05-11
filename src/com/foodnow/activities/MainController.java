package com.foodnow.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.foodnow.R;
import com.foodnow.views.MainView;

public class MainController extends Activity {
    private MainView.ViewListener viewListener = new MainView.ViewListener() {
        @Override
        public void onEventOne() {
            Intent intent = new Intent(MainController.this, DemoController.class);
            startActivity(intent);
        }

        @Override
        public void onEventTwo() {

        }

    };

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainView view = (MainView) View.inflate(this, R.layout.main, null);
        view.setViewListener(viewListener);
        setContentView(view);
    }
}

