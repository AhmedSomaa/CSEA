package com.sample.ahmed.csea.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.ahmed.csea.Adapters.CircleTransform;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

public class Activity_TA_Profile extends AppCompatActivity {

    ImageView TA_pic_iv;
    ImageButton call_TA_iv, msg_TA_iv, email_TA_iv;
    TextView TA_name_tv, TA_office_hours_tv, TA_email_tv, TA_phone_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ta__profile);

        //  setting up the views
        TA_pic_iv = (ImageView)findViewById(R.id.ta_profile_pic);
        TA_name_tv = (TextView)findViewById(R.id.ta_profile_name);
        TA_office_hours_tv = (TextView)findViewById(R.id.ta_profile_officeHours);
        TA_email_tv = (TextView) findViewById(R.id.ta_profile_email);
        TA_phone_tv = (TextView) findViewById(R.id.ta_profile_num);
        call_TA_iv = (ImageButton) findViewById(R.id.call_ta);
        msg_TA_iv = (ImageButton)findViewById(R.id.message_ta);
        email_TA_iv = (ImageButton)findViewById(R.id.email_ta);

        final Bundle bundle = getIntent().getExtras();
        Picasso.with(Activity_TA_Profile.this).load(bundle.getInt("TA_Pic")).transform(new CircleTransform()).into(TA_pic_iv);
        TA_name_tv.setText(bundle.getString("TA_Name"));
        TA_office_hours_tv.setText(bundle.getString("TA_OfficeHours"));
        TA_email_tv.setText(bundle.getString("TA_Email"));
        TA_phone_tv.setText(bundle.getString("TA_Phone"));

        email_TA_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email_intent = new Intent(Intent.ACTION_SEND);
                email_intent.putExtra(Intent.EXTRA_EMAIL, new String[] {bundle.getString("TA_Email")});
                email_intent.setType("text/plain");
                startActivity(Intent.createChooser(email_intent, "Send Email"));
            }
        });

        call_TA_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call_intent = new Intent(Intent.ACTION_DIAL);
                call_intent.setData(Uri.parse("tel:"+ bundle.getString("TA_Phone")));
                startActivity(call_intent);
            }
        });


        msg_TA_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent msg_intent = new Intent(Intent.ACTION_SEND);
                String title = getResources().getString(R.string.chooser_title);
                Intent chooser = Intent.createChooser(msg_intent, title);
                startActivity(Intent.createChooser(msg_intent, title));
            }
        });

    }
}
