package com.sample.ahmed.csea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sample.ahmed.csea.Activities.Activity_Detail;
import com.sample.ahmed.csea.Activities.Activity_Professor;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.Models.Model_Professor;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.myViewHolder>{

    private Context context;
    private List<Model_Professor> mData;
    String imageURL;


    public ProfessorAdapter(Context context, List<Model_Professor> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardview_model, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, final int position) {

        // display the professor photo and Name
        holder.tv_head.setText(mData.get(position).getProf_name());
        imageURL = "https://s3-ap-southeast-2.amazonaws.com/appcsea/professors/pics/"+ mData.get(position).getProf_imgURL();
        Picasso.with(context).load(imageURL).into(holder.iv_icon);

        // setting onClick Listener
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String prof_name = mData.get(position).getProf_name();
                String prof_position = mData.get(position).getProf_pos();
                String prof_email = mData.get(position).getProf_email();
                String prof_office = mData.get(position).getProf_office();
                String prof_brief = null;
                try {
                    prof_brief = new String(mData.get(position).getProf_brief().getBytes("ISO-8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String prof_research = null;
                try {
                    prof_research = new String (mData.get(position).getProf_research().getBytes("ISO-8859-1"), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String prof_publication = null;
                try {
                    prof_publication = new String(mData.get(position).getProf_publication().getBytes("ISO-8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String prof_courses = mData.get(position).getProf_courses();
                String prof_imgURL = mData.get(position).getProf_imgURL();

                String picURL = "https://s3-ap-southeast-2.amazonaws.com/appcsea/professors/pics/"+ prof_imgURL;

                // display the item page onClick with the its data passed
                Intent prof_intent = new Intent(context, Activity_Professor.class);
                prof_intent.putExtra("PictureURL", picURL);
                prof_intent.putExtra("Name", prof_name);
                prof_intent.putExtra("Position", prof_position);
                prof_intent.putExtra("Email", prof_email);
                prof_intent.putExtra("Office", prof_office);
                prof_intent.putExtra("Bio", prof_brief);
                prof_intent.putExtra("Research", prof_research);
                prof_intent.putExtra("Publications", prof_publication);
                prof_intent.putExtra("Courses", prof_courses);
                prof_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(prof_intent);
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

