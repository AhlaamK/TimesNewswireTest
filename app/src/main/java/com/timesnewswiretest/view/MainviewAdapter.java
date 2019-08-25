package com.timesnewswiretest.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.timesnewswiretest.R;

import java.util.List;

import Model.NewsModel;
import view.NewsDetailsActivity;

/**
 * Created by AhlaamK on 20/8/2019
 */

public class MainviewAdapter extends RecyclerView.Adapter<MainviewAdapter.ViewHolder> {

    private final Context mContext;
    private final List<NewsModel> newsModels;
    private String datetimeStr,urlStr;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_news, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        NewsModel news = newsModels.get(i);


       viewHolder.abstract_Textview.setText(news.getAbstracts());
       viewHolder.byline_Textview.setText(news.getByline());
        datetimeStr=news.getPublished_date();
        viewHolder.published_date_Textview.setText(datetimeStr.substring(0, 10));
        urlStr= news.getUrl();

        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.WHITE)
                .cornerRadiusDp(50)
                .oval(false)
                .build();
      //  viewHolder.newtextview.setText(Html.fromHtml("https://www.nytimes.com/2019/08/21/briefing/national-rifle-association-greenland-migrants.html", 2));
        if(news.getThumbnail_standard() != null && !news.getThumbnail_standard().isEmpty()) {
            Picasso.with(mContext).load(news.getThumbnail_standard()).transform(transformation).into(viewHolder.newsImageview);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra("UrlName", newsModels.get(i).getUrl());
                intent.putExtra("Abstract", newsModels.get(i).getAbstracts());
                intent.putExtra("NewsTitle", newsModels.get(i).getTitle());
                intent.putExtra("Byline", newsModels.get(i).getByline());
                intent.putExtra("Newsimage", newsModels.get(i).getThumbnail_standard());

                mContext.startActivity(intent);
/*
             Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsModels.get(i).getUrl()));
                mContext.startActivity(browserIntent);*/

            }
        });


    }

    public MainviewAdapter(Context context, List<NewsModel> dataList) {
        this.mContext = context;
        this.newsModels = dataList;
    }


    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView newtextview;
        ImageView newsImageview,urlImageview;
        TextView abstract_Textview,byline_Textview,published_date_Textview;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            abstract_Textview =itemView.findViewById(R.id.abstract_Textview);
           // newtextview = itemView.findViewById(R.id.newtextview);
            newsImageview = itemView.findViewById(R.id.newsImageview);
            byline_Textview = itemView.findViewById(R.id.byline_Textview);
            published_date_Textview = itemView.findViewById(R.id.published_date_Textview);


        }
    }
}
