package com.sample.ahmed.csea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import com.sample.ahmed.csea.Activities.Activity_TA_Reservation;
import com.sample.ahmed.csea.Models.Model_TA;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

public class ListViewAdapter_TA extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<Model_TA> itemModelsList;
    ArrayList<Model_TA> itemModelArrayList;

    public ListViewAdapter_TA(Context context, List<Model_TA> itemModelList) {
        this.mContext = context;
        this.itemModelsList = itemModelList;
        //inflater = LayoutInflater.from(mContext);
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemModelArrayList = new ArrayList<Model_TA>();
        this.itemModelArrayList.addAll(itemModelList);
    }

    public class ViewHolder{
        TextView mTitleTv, mRateTv;
        ImageView mIconIv;
    }

    @Override
    public int getCount() {
        return itemModelsList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemModelsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder viewholder;
        if(view == null)
        {
            viewholder = new ViewHolder();
            view = inflater.inflate(R.layout.ta_lv_row, null);

            // locate the views in list_view_row.xml
            viewholder.mTitleTv = (TextView) view.findViewById(R.id.ta_name);
            viewholder.mIconIv = (ImageView) view.findViewById(R.id.ta_img);
            viewholder.mRateTv = (TextView) view.findViewById(R.id.ta_rating);

            view.setTag(viewholder);
        }   else {
            viewholder = (ViewHolder) view.getTag();
        }

        //set the results into text views
        viewholder.mTitleTv.setText(itemModelsList.get(position).getName());
        Picasso.with(mContext).load(itemModelArrayList.get(position).getImgURL()).transform(new CircleTransform()).into(viewholder.mIconIv);
        viewholder.mRateTv.setText(itemModelsList.get(position).getRating());


        // listView item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getting all info about one object in array list
                String TA_officeHrs = itemModelsList.get(position).getOfficeHours();
                String TA_name = itemModelsList.get(position).getName();
                String TA_mail = itemModelsList.get(position).getMail();
                String TA_rate = itemModelsList.get(position).getRating();
                String TA_phone = itemModelsList.get(position).getPhone();
                int TA_imageURL = itemModelsList.get(position).getImgURL();
                
                //sendMessage(TA_name, TA_mail);
                Intent TA_intent = new Intent (mContext, Activity_TA_Reservation.class);
                TA_intent.putExtra("TA_officHrs", TA_officeHrs);
                TA_intent.putExtra("TA_name", TA_name);
                TA_intent.putExtra("TA_mail", TA_mail);
                TA_intent.putExtra("TA_rate", TA_rate);
                TA_intent.putExtra("TA_phone", TA_phone);
                TA_intent.putExtra("TA_imageURL", TA_imageURL);
                mContext.startActivity(TA_intent);

            }
        });

        return view;
    }

    //filter by heading and subheading
    /*public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        itemModelsList.clear();
        if (charText.length()==0){
            itemModelsList.addAll(itemModelArrayList);
        }
        else {
            for (Model_TA model : itemModelArrayList){
                if (model.get().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getDetails().toLowerCase(Locale.getDefault()).contains(charText)){
                    itemModelsList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }*/
}
