package com.sample.ahmed.csea.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

public class Activity_News extends AppCompatActivity {

    private ImageView pic_iv;
    private TextView header_tv, detail_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__news);

        //  Setting up the views
        header_tv = (TextView)findViewById(R.id.event_name);
        pic_iv = (ImageView)findViewById(R.id.event_image);
        detail_tv = (TextView)findViewById(R.id.event_detail);

        Bundle bundle = getIntent().getExtras();

        String eventPic =  bundle.getString("eventPhotoURL");
        String eventHeading = null;
        try {
            eventHeading = new String(bundle.getString("eventHeading").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String eventDetail = null;
        try {
            eventDetail = new String(bundle.getString("eventDetail").getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(eventHeading);


        //  displaying the data
        header_tv.setText(eventHeading);
        Picasso.with(Activity_News.this).load(eventPic).into(pic_iv);
        detail_tv.setText(eventDetail);

    }
}
