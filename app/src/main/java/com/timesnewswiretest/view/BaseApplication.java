package com.timesnewswiretest.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AhlaamK on 24/8/2019
 */
public class BaseApplication extends Application {
    private static final String NEWS_LIST = "news_list";

    public static void setNewsList(Activity activity, String newsListGson) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(NEWS_LIST, newsListGson);
        editor.apply();
    }

    public static String getNewsList(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString(NEWS_LIST, "n/a");
    }
}
