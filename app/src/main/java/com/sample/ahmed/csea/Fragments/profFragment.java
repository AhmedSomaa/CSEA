package com.sample.ahmed.csea.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sample.ahmed.csea.Adapters.ListViewAdapter_Professor;
import com.sample.ahmed.csea.Adapters.ListViewAdapter_TA;
import com.sample.ahmed.csea.Models.Model_Professor;
import com.sample.ahmed.csea.Models.Model_TA;
import com.sample.ahmed.csea.R;

import java.util.ArrayList;


public class profFragment extends Fragment {

    ListView listView;
    ListViewAdapter_Professor adapter;
    ArrayList<Model_Professor> arrayList = new ArrayList<Model_Professor>();

    public profFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clininc_prof_layout, container, false);

        listView = view.findViewById(R.id.lv_prof);

        // adding the items to be displayed
        arrayList.add(new Model_Professor("Ahmed Refaay", "refaay@aucegypt.edu", "C:/Users/Ahmed/Desktop/refaay.jpg"));

        // display adapter
        adapter = new ListViewAdapter_Professor(getContext(), arrayList);
        listView.setAdapter(adapter);

        return view;
    }
}
