package com.timesnewswiretest.view;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.timesnewswiretest.R;

public  class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView back_ImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        ActionBar localActionBar = this.getSupportActionBar();
        localActionBar.setCustomView(R.layout.customize_toolbar);
        getSupportActionBar().setTitle("My custom toolbar!");
       localActionBar.setHomeButtonEnabled(true);
        localActionBar.setDisplayHomeAsUpEnabled(true);
        localActionBar.getTitle();
        localActionBar.setDisplayShowCustomEnabled(false);
        localActionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
        localActionBar.setDisplayShowTitleEnabled(false);

        back_ImageView = (ImageView) findViewById(R.id.back_ImageView);
     //   back_ImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back_ImageView)
            this.finish();

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
        }




