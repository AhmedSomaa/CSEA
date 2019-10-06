package com.sample.ahmed.csea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.ahmed.csea.Activities.Activity_Course;
import com.sample.ahmed.csea.Activities.Activity_Professor;
import com.sample.ahmed.csea.Models.Model_Course;
import com.sample.ahmed.csea.Models.Model_Professor;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.myViewHolder>{

    private Context context;
    private List<Model_Course> mData;
    String imageURL;


    public CourseAdapter(Context context, List<Model_Course> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_course_model, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        // display the professor photo and Name
        holder.tv_head.setText(mData.get(position).getCourse_code());
        imageURL = "https://s3-ap-southeast-2.amazonaws.com/appcsea/courses/pics/"+ mData.get(position).getCourse_imgURL();
        Picasso.with(context).load(imageURL).into(holder.iv_icon);

        // setting onClick Listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String course_code = mData.get(position).getCourse_code();
                String course_name = mData.get(position).getCourse_name();
                String course_credits = mData.get(position).getCourse_credits();
                String course_prerequisite = mData.get(position).getCourse_prerequisite();
                String course_description = null;
                try {
                    course_description = new String (mData.get(position).getCourse_description().getBytes("ISO-8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String course_when_offered = mData.get(position).getCourse_when_offered();
                String course_imgURL = mData.get(position).getCourse_imgURL();

                String picURL = "https://s3-ap-southeast-2.amazonaws.com/appcsea/courses/pics/"+ course_imgURL;

                // display the item page onClick with the its data passed
                Intent course_intent = new Intent(context, Activity_Course.class);
                course_intent.putExtra("CoursePictureURL", picURL);
                course_intent.putExtra("CourseCode", course_code);
                course_intent.putExtra("CourseName", course_name);
                course_intent.putExtra("CourseCredits", course_credits);
                course_intent.putExtra("CoursePrerequisite", course_prerequisite);
                course_intent.putExtra("CourseDescription", course_description);
                course_intent.putExtra("CourseWhenOffered", course_when_offered);
                course_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(course_intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{


        TextView tv_head;
        ImageView iv_icon;
        CardView cardView;

        public myViewHolder(View itemView) {
            super(itemView);
            tv_head = (TextView) itemView.findViewById(R.id.cardview_title);
            iv_icon = (ImageView) itemView.findViewById(R.id.cardview_image);
            cardView = (CardView) itemView.findViewById(R.id.cardView_id);
        }
    }

}

