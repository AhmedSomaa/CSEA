package com.sample.ahmed.csea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.ahmed.csea.Activities.Activity_Professor_Reservation;
import com.sample.ahmed.csea.Activities.Activity_TA_Reservation;
import com.sample.ahmed.csea.Models.Model_Professor;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter_Professor extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<Model_Professor> itemModelsList;
    ArrayList<Model_Professor> itemModelArrayList;

    public ListViewAdapter_Professor(Context context, List<Model_Professor> itemModelList) {
        this.mContext = context;
        this.itemModelsList = itemModelList;
        //inflater = LayoutInflater.from(mContext);
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemModelArrayList = new ArrayList<Model_Professor>();
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
            view = inflater.inflate(R.layout.professor_lv_row, null);

            // locate the views in list_view_row.xml
            viewholder.mTitleTv = (TextView) view.findViewById(R.id.prof_name);
            viewholder.mIconIv = (ImageView) view.findViewById(R.id.prof_img);

            view.setTag(viewholder);
        }   else {
            viewholder = (ViewHolder) view.getTag();
        }

        //set the results into text views
        viewholder.mTitleTv.setText(itemModelsList.get(position).getProf_name());
        //Picasso.with(mContext).load(itemModelArrayList.get(position).getProf_imgURL()).transform(new CircleTransform()).into(viewholder.mIconIv);
        viewholder.mIconIv.setImageBitmap(BitmapFactory.decodeFile(itemModelArrayList.get(position).getProf_imgURL()));

        // listView item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getting all info about one object in array list
                String Prof_name = itemModelsList.get(position).getProf_name();
                String Prof_mail = itemModelsList.get(position).getProf_email();
                
                //sendMessage(TA_name, TA_mail);
                Intent TA_intent = new Intent (mContext, Activity_Professor_Reservation.class);
                TA_intent.putExtra("TA_name", Prof_name);
                TA_intent.putExtra("TA_mail", Prof_mail);
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
