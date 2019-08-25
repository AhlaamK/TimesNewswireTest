package view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.timesnewswiretest.R;
import com.timesnewswiretest.view.BaseActivity;

import java.util.List;

import Model.NewsModel;

public class NewsDetailsActivity extends BaseActivity {
    private TextView newstitle_textView,abstractdetails_textView,bylinedetails_textView,readmore_textView;
    private ImageView news_imageView;

    String UrlName;
    String NewsTitle ;
    String Byline ;
    String Newsimage;
    String Abstract;
    List<NewsModel> newsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_details);
        intializeViews();


        Intent intent = getIntent();
        if(intent!= null){
             UrlName = intent.getStringExtra("UrlName");
             NewsTitle = intent.getStringExtra("NewsTitle");
            Abstract = intent.getStringExtra("Abstract");
            Byline = intent.getStringExtra("Byline");
             Newsimage = intent.getStringExtra("Newsimage");
        }

            newstitle_textView.setText(NewsTitle);
        abstractdetails_textView.setText(Abstract);
        bylinedetails_textView .setText(Byline);
        if(Newsimage != null && !Newsimage.isEmpty()) {
            Picasso.with(getApplicationContext()).load(Newsimage).into(news_imageView);

        }
        readmore_textView.setText("ReadMore: "+ UrlName);


       /* StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DownloadWebPageTask task = new DownloadWebPageTask(UrlName);
        task.execute();*/


     /*   URL url = null;
        try {
            url = new URL(UrlName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


      //  newdetails_textView.setText(Html.fromHtml(UrlName));
    }


   /* private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
        String urlname;
        public DownloadWebPageTask(String urlName) {
            this.urlname= urlName;

        }

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
          //  for (String url : urls) {
                DefaultHttpClient client = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(urlname);
                try {
                    HttpResponse execute = client.execute(httpGet);
                    InputStream content = execute.getEntity().getContent();

                    BufferedReader buffer = new BufferedReader(
                            new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
          //  }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            newdetails_textView.setText(Html.fromHtml(result));
        }
    }*/

   public void intializeViews(){
       newstitle_textView = findViewById(R.id.newstitle_textView);
       abstractdetails_textView = findViewById(R.id.abstractdetails_textView);
       bylinedetails_textView = findViewById(R.id.bylinedetails_textView);
       news_imageView = findViewById(R.id.news_imageView);
       readmore_textView= findViewById(R.id.readmore_textView);


   }


}

