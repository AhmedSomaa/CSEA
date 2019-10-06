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
import com.sample.ahmed.csea.Activities.Activity_News;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.Models.Model_News;
import com.sample.ahmed.csea.R;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NewsAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    List<Model_News> itemModelsList;
    ArrayList<Model_News> itemModelArrayList;

    public NewsAdapter(Context context, List<Model_News> itemModelList) {
        this.mContext = context;
        this.itemModelsList = itemModelList;
        inflater = LayoutInflater.from(mContext);
        this.itemModelArrayList = new ArrayList<Model_News>();
        this.itemModelArrayList.addAll(itemModelList);
    }

    public class ViewHolder{
        TextView categoryName_tv, newsHeading_tv;
        ImageView categoryIcon_iv, newsPhoto_iv;
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
            view = inflater.inflate(R.layout.lv_news_row, null);

            // locate the views in list_view_row.xml
            viewholder.categoryIcon_iv = (ImageView) view.findViewById(R.id.category_icon);
            viewholder.categoryName_tv = (TextView) view.findViewById(R.id.tv_category);
            viewholder.newsHeading_tv = (TextView) view.findViewById(R.id.new_heading);
            viewholder.newsPhoto_iv = (ImageView) view.findViewById(R.id.cv_img);

            view.setTag(viewholder);
        }   else {
            viewholder = (ViewHolder) view.getTag();
        }

        //set the results into text views

        String categoryImageURL = "https://s3-ap-southeast-2.amazonaws.com/appcsea/news/icons/" + itemModelsList.get(position).getCategoryLogo();
        String eventImageURL = "https://s3-ap-southeast-2.amazonaws.com/appcsea/news/images/" + itemModelsList.get(position).getEventImage();

        Picasso.with(mContext).load(categoryImageURL).into(viewholder.categoryIcon_iv);
        viewholder.categoryName_tv.setText(itemModelsList.get(position).getCategoryName());
        Picasso.with(mContext).load(eventImageURL).into(viewholder.newsPhoto_iv);
        String heading = null;
        try {
            heading = new String (itemModelsList.get(position).getEventHeading().getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        viewholder.newsHeading_tv.setText(heading);


        // listView item Click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // display the item page onClick with the its data passed
                Intent news_intent = new Intent(mContext, Activity_News.class);
                news_intent.putExtra("eventPhotoURL", "https://s3-ap-southeast-2.amazonaws.com/appcsea/news/images/" + itemModelsList.get(position).getEventImage());
                news_intent.putExtra("eventHeading", itemModelsList.get(position).getEventHeading());
                news_intent.putExtra("eventDetail", itemModelArrayList.get(position).getEventDetail());
                news_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(news_intent);
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
            for (Model_News model : itemModelArrayList){
                if (model.getCategoryName().toLowerCase(Locale.getDefault())
                        .contains(charText) || model.getEventHeading().toLowerCase(Locale.getDefault()).contains(charText)){
                    itemModelsList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
