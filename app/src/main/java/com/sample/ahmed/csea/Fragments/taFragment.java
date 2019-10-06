package com.sample.ahmed.csea.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sample.ahmed.csea.Adapters.ListViewAdapter_TA;
import com.sample.ahmed.csea.Models.Model_TA;
import com.sample.ahmed.csea.R;

import java.util.ArrayList;


public class taFragment extends Fragment {


    ListView listView;
    ListViewAdapter_TA adapter;
    ArrayList<Model_TA> arrayList = new ArrayList<Model_TA>();

    //constructor
    public taFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clininc_ta_layout, container, false);
        arrayList.clear();
        // setting up the List view
        listView = view.findViewById(R.id.lv_ta);

        // adding the items to be displayed
        arrayList.add(new Model_TA("UW 11:00 - 01:00\nMR 11:00 - 01:00", "Ahmed Esmaiel", "ahmedsomaa@aucegypt.edu", "4.8", "01009408401", R.drawable.somaa));
        arrayList.add(new Model_TA("UW 01:00 - 02:00\nMR 01:00 - 02:00", "Lotfy Hussein", "lotfyhussein@aucegypt.edu", "4.9", "01273176460", R.drawable.lotfy));
        arrayList.add(new Model_TA("UW 08:30 - 10:00\nMR 08:30 - 10:00", "Ahmed Refaay", "refaay@aucegypt.edu","5.0", "0106716552",  R.drawable.refaay));


        // display adapter
        adapter = new ListViewAdapter_TA(getContext(), arrayList);
        listView.setAdapter(adapter);

        return view;
    }
}
