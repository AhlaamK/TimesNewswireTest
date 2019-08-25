package util;
/**
 * Created by AhlaamK on 19/8/2019
 */

import android.content.Context;
import android.net.ConnectivityManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public  static String BASE_URL = "https://api.nytimes.com/svc/news/v3/content/all/" ;
    public  static String API_KEY = "KXpIzUKr9M0xz7Eo3q7Kw2I4dqVcs0mM" ;
    public static boolean isNetworkConnected(Context context) {
        if (context == null)
            return false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null;
    }

    public static String getcurrentDateTime(String datetimeStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateTime= null;
        try {
            dateTime = sdf.parse(datetimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime.toString();

    }
}
