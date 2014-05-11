package com.foodnow.activities;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import com.foodnow.R;
import com.foodnow.loaders.DemoLoader;
import com.foodnow.models.DemoModel;
import com.foodnow.views.DemoView;

/**
 * @author lifenoodles
 *         created on 31/03/2014.
 */
public class DemoController extends Activity {
    private static final int ID_CALENDAR_LOADER = 0;
    private DemoView view;
    private DemoModel model;

    private DemoView.ViewListener viewListener = new DemoView.ViewListener() {
        @Override
        public void onTextClicked() {
            model.setUpdating(true);
            getLoaderManager().initLoader(ID_CALENDAR_LOADER, null, new CalendarLoaderHandler());
        }
    };

    public DemoController setCalendarModel(DemoModel model) {
        this.model = model;
        view.setModel(model);
        return this;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getLoaderManager is actually needed to be called here or the loaders magically
        // won't work, wtf is this bullshit
        getLoaderManager();
        view = (DemoView) View.inflate(this, R.layout.demo, null);
        view.setViewListener(viewListener);
        setCalendarModel(new DemoModel());
        setContentView(view);
    }

    private class CalendarLoaderHandler implements LoaderManager.LoaderCallbacks<DemoModel> {
        @Override
        public Loader<DemoModel> onCreateLoader(int i, Bundle bundle) {
            return new DemoLoader(DemoController.this);
        }

        @Override
        public void onLoadFinished(Loader<DemoModel> calendarModelLoader, DemoModel calendarModel) {
            DemoController.this.setCalendarModel(calendarModel);
            calendarModel.onUpdated();
        }

        @Override
        public void onLoaderReset(Loader<DemoModel> calendarModelLoader) { }
    }
}