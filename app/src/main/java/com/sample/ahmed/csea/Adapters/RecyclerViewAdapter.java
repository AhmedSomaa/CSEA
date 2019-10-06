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

import com.sample.ahmed.csea.Activities.Activity_Detail;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.myViewHolder>{

    private Context context;
    private List<Model> mData;


    public RecyclerViewAdapter(Context context, List<Model> mData) {
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

        holder.tv_head.setText(mData.get(position).getHeading());
        holder.iv_icon.setImageResource(mData.get(position).getImg());
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String item_tit = mData.get(position).getHeading();
                String item_sbt = mData.get(position).getSubheading();
                String item_det = mData.get(position).getDetails();
                int item_img = mData.get(position).getImg();

                // display the item page onClick with the its data passed
                Intent displayMyDetail = new Intent(context, Activity_Detail.class);
                displayMyDetail.putExtra("title", item_tit);
                displayMyDetail.putExtra("subtitle", item_sbt);
                displayMyDetail.putExtra("detail", item_det);
                displayMyDetail.putExtra("icon", item_img);
                context.startActivity(displayMyDetail);
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

