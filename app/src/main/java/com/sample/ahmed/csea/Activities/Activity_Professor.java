package com.sample.ahmed.csea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sample.ahmed.csea.Adapters.CircleTransform;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

public class Activity_Professor extends AppCompatActivity {

    ImageView profImage_iv;
    TextView profName_tv, profPosition_tv;
    TextView profEmail_tv, profOffice_tv;
    TextView profBio_tv, profResearch_tv, profPublications_tv, profCourses_tv;
    RelativeLayout email_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__professor);

        //  setting the views
        profImage_iv = (ImageView)findViewById(R.id.prof_img);
        profName_tv = (TextView)findViewById(R.id.prof_name);
        profPosition_tv = (TextView)findViewById(R.id.prof_position);
        profEmail_tv = (TextView)findViewById(R.id.prof_email);
        profOffice_tv = (TextView)findViewById(R.id.prof_office);
        profBio_tv = (TextView)findViewById(R.id.prof_bio);
        profResearch_tv = (TextView)findViewById(R.id.prof_research);
        profPublications_tv = (TextView)findViewById(R.id.prof_publications);
        profCourses_tv = (TextView)findViewById(R.id.prof_courses);
        email_rl = (RelativeLayout)findViewById(R.id.email_relative);


        //  retrieving professor info
        final Bundle bundle = getIntent().getExtras();
        Picasso.with(Activity_Professor.this).load(bundle.getString("PictureURL")).transform(new CircleTransform()).into(profImage_iv);
        profName_tv.setText(bundle.getString("Name"));
        profPosition_tv.setText(bundle.getString("Position"));
        profEmail_tv.setText(bundle.getString("Email"));
        profOffice_tv.setText(bundle.getString("Office"));
        profBio_tv.setText(bundle.getString("Bio"));
        profResearch_tv.setText(bundle.getString("Research"));
        profPublications_tv.setText(bundle.getString("Publications"));
        profCourses_tv.setText(bundle.getString("Courses"));

        //  send email to
        email_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {bundle.getString("Email")});
                intent.setType("text/plain");
                //intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });


    }
}
