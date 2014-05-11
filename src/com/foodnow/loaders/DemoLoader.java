package com.foodnow.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.foodnow.models.DemoModel;

public class DemoLoader extends AsyncTaskLoader<DemoModel> {
    public DemoLoader(Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged()) {
            forceLoad();
        }
    }

    @Override
    public DemoModel loadInBackground() {
        DemoModel model = new DemoModel();
        String html = "Some Random String";
        try {
            Thread.sleep(5000);
        } catch(Exception e) {
//            LOLNO
        }
        model.setData(html);
        return model;
    }
}
