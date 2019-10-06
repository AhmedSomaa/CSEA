package com.sample.ahmed.csea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.ahmed.csea.R;

public class Activity_Detail extends AppCompatActivity {

   TextView tvd_heading, tvd_details, tvd_heading2, tvd_des;
   ImageView ivd_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__detail);


        Bundle bundle = getIntent().getExtras();
        String t = bundle.getString("title");
        String d = bundle.getString("subtitle");
        String s = bundle.getString("detail");
        int im_id = bundle.getInt("icon");

        tvd_heading = (TextView)findViewById(R.id.tv_heading);
        tvd_heading.setText(t);

        tvd_details = (TextView)findViewById(R.id.details1);
        tvd_details.setText(d);

        tvd_heading2 = (TextView)findViewById(R.id.heading2);
        tvd_heading2.setText(t);

        ivd_img = (ImageView)findViewById(R.id.cv_img);
        ivd_img.setImageResource(im_id);

        tvd_des = (TextView)findViewById(R.id.des);
        tvd_des.setText(s);

    }
}
