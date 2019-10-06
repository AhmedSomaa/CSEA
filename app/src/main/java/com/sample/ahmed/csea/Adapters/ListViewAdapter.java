package com.sample.ahmed.csea.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.ahmed.csea.Activities.Activity_Detail;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<Model> itemModelsList;
    ArrayList<Model> itemModelArrayList;

    public ListViewAdapter(Context context, List<Model> itemModelList) {
        mContext = context;
        this.itemModelsList = itemModelList;
        inflater = LayoutInflater.from(mContext);
        this.itemModelArrayList = new ArrayList<Model>();
        this.itemModelArrayList.addAll(itemModelList);
    }

    public class ViewHolder{
        TextView mTitleTv, mDescrTv;
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
            view = inflater.inflate(R.layout.intern_lv_row, null);

            // locate the views in list_view_row.xml
            viewholder.mTitleTv = (TextView) view.findViewById(R.id.tv_heading);
            viewholder.mDescrTv = (TextView) view.findViewById(R.id.tv_details);
            viewholder.mIconIv = (ImageView) view.findViewById(R.id.cv_img);

            view.setTag(viewholder);
        }   else {
            viewholder = (ViewHolder) view.getTag();
        }

        //set the results into text views
        viewholder.mTitleTv.setText(itemModelsList.get(position).getHeading());
        viewholder.mDescrTv.setText(itemModelsList.get(position).getSubheading());
        viewholder.mIconIv.setImageResource(itemModelsList.get(position).getImg());


        //String str1 = itemModelsList.get(position).getIcon();
        //String str2 = str1.substring(0, str1.length()-4);
        /*int imageID = mContext.getResources().getIdentifier(str2, "drawable", mContext.getPackageName());
        viewholder.mIconIv.setImageResource(imageID);*/


        // listView item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getting all info about one object in array list
                String item_tit = itemModelsList.get(position).getHeading();
                String item_sbt = itemModelsList.get(position).getSubheading();
                String item_det = itemModelsList.get(position).getDetails();
                int item_img = itemModelsList.get(position).getImg();

                // display the item page onClick with the its data passed
                Intent displayMyDetail = new Intent(mContext, Activity_Detail.class);
                displayMyDetail.putExtra("title", item_tit);
                displayMyDetail.putExtra("subtitle", item_sbt);
                displayMyDetail.putExtra("detail", item_det);
                displayMyDetail.putExtra("icon", item_img);
                mContext.startActivity(displayMyDetail);
            }
        });

        return view;
    }

    //filter by heading and subheading
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        itemModelsList.clear();
        if (charText.length()==0){
            itemModelsList.addAll(itemModelArrayList);
        }
        else {
            for (Model model : itemModelArrayList){
                if (model.getHeading().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getDetails().toLowerCase(Locale.getDefault()).contains(charText)){
                    itemModelsList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }

}
