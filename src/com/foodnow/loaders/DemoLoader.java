package com.foodNow.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import com.foodNow.models.DemoModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
        String html = "";
        String url = "http://www.just-eat.ie/area/ashtown-dublin-15";
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.131 Safari/537.36").get();
            //System.out.print(doc.toString());
            Elements links = doc.select("h3");
            for (Element link : links)
                html += link.text() + "\n";


            Thread.sleep(5000);
        } catch(Exception e) {
//            LOLNO
        }
        model.setData(html);
        return model;
    }
}
