package com.sample.ahmed.csea.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.ahmed.csea.Adapters.CircleTransform;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Activity_Course extends AppCompatActivity {

    ImageView courseImg_iv;
    TextView courseCode_tv, courseNameCredits_tv;
    TextView coursePrereq_tv, courseDescr_tv, courseWhen_tv;
    LinearLayout courseChatGroup_ll;
    String Ccode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__course);

        //  setting up the views
        courseImg_iv = (ImageView)findViewById(R.id.course_img);
        courseCode_tv = (TextView)findViewById(R.id.course_code);
        courseNameCredits_tv = (TextView)findViewById(R.id.course_name);
        coursePrereq_tv = (TextView)findViewById(R.id.course_pre);
        courseDescr_tv = (TextView)findViewById(R.id.course_desc);
        courseWhen_tv = (TextView)findViewById(R.id.course_when);
        courseChatGroup_ll = (LinearLayout)findViewById(R.id.course_linear_1);


        //  retrieving course info
        Bundle bundle = getIntent().getExtras();
        Picasso.with(Activity_Course.this).load(bundle.getString("CoursePictureURL")).transform(new CircleTransform()).into(courseImg_iv);
        courseCode_tv.setText(bundle.getString("CourseCode"));
        Ccode = bundle.getString("CourseCode");
        courseNameCredits_tv.setText(bundle.getString("CourseName")+" ("+bundle.getString("CourseCredits")+")");
        coursePrereq_tv.setText(bundle.getString("CoursePrerequisite"));
        courseDescr_tv.setText(bundle.getString("CourseDescription"));
        courseWhen_tv.setText(bundle.getString("CourseWhenOffered"));

        courseChatGroup_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Activity_Course.this, "Heading to Chat!!!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Activity_Course.this, Activity_Chat.class);
                i.putExtra("courseCode", Ccode );
                startActivity(i);
            }
        });

    }
}
