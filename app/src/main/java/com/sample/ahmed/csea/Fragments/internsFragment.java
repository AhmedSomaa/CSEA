package com.sample.ahmed.csea.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sample.ahmed.csea.Activities.Activity_Login;
import com.sample.ahmed.csea.Adapters.ListViewAdapter;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.R;

import java.util.ArrayList;

public class internsFragment extends Fragment {


    // arrays to hold the data
    String[] title;
    String[] subtitle;
    String[] detail;
    int[] icon;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    // setters for data display
    ListView rv_intern;
    ListViewAdapter adapter;
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Setting up the view
        View view = inflater.inflate(R.layout.layout_interns, container,false);

        // setting the title of the action bar
        getActivity().setTitle("Internships");

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(getActivity(), Activity_Login.class));
                    getActivity().finish();
                }
            }
        };


        // setting up the List view
        rv_intern = view.findViewById(R.id.rv_intern);


        // adding the items to be displayed
        arrayList.add(new Model(R.drawable.ic_work_black_24dp, "Internships", "Internship 1", "Internship 1 details....."));
        arrayList.add(new Model(R.drawable.ic_work_black_24dp, "Internships", "Internship 2", "Internship 12 details....."));
        arrayList.add(new Model(R.drawable.ic_work_black_24dp, "Internships", "Internship 3", "Internship 3 details....."));
        arrayList.add(new Model(R.drawable.ic_work_black_24dp, "Internships", "Internship 4", "Internship 4 details....."));
        arrayList.add(new Model(R.drawable.ic_work_black_24dp, "Internships", "Internship 5", "Internship 5 details....."));

        // setting the view adapter
        adapter = new ListViewAdapter(getContext(), arrayList);
        rv_intern.setAdapter(adapter);
        return view;
    }


    // the menu to be displayed at Action bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_model_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.news_search:
                search(item);
                return true;
            case R.id.return_home:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void logout(){
        auth.signOut();
        startActivity(new Intent(getActivity(), Activity_Login.class));
    }

    public void search(MenuItem item){
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Search here...");

        // handeling the search function
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //gets called with every input string. newTex = input String
            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    adapter.filter("");
                    rv_intern.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });

    }
}
