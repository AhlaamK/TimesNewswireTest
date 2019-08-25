package Model;

import com.google.gson.annotations.SerializedName;
/**
 * Created by AhlaamK on 20/8/2019
 */

public class NewsModel{
    @SerializedName("title")
   // @Bindable
    private final String title;

    @SerializedName("byline")
    //@Bindable
    private final String byline;

    @SerializedName("published_date")
    //@Bindable
    private final String published_date;


    @SerializedName("thumbnail_standard")
    //@Bindable
    private final String thumbnail_standard;

    @SerializedName("url")
   // @Bindable
    private final String url;



    @SerializedName("abstract")
   // @Bindable
    private final String abstracts;



    public NewsModel(String byline, String title, String published_date, String thumbnail_standard, String url
    ,String abstracts) {
        this.title = title;
        this.byline = byline;
        this.published_date = published_date;
        this.thumbnail_standard = thumbnail_standard;
        this.url = url;
        this.abstracts = abstracts;
      /*  notifyPropertyChanged(BR.title);
        notifyPropertyChanged(BR.byline);
        notifyPropertyChanged(BR.published_date);*/
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getThumbnail_standard() {
        return thumbnail_standard;
    }
    public String getUrl() {
        return url;
    }
    public String getAbstracts() {
        return abstracts;
    }
}
