package com.sample.ahmed.csea.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sample.ahmed.csea.Activities.Activity_Login;
import com.sample.ahmed.csea.Adapters.ListViewAdapter;
import com.sample.ahmed.csea.Adapters.NewsAdapter;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.Models.Model_News;
import com.sample.ahmed.csea.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class newsFragment extends Fragment {


    // arrays to hold the data
    String[] title;
    String[] subtitle;
    String[] detail;
    int[] icon;


    // setters for data display
    ListView listView;
    NewsAdapter adapter;
    ArrayList<Model_News> arrayList = new ArrayList<Model_News>();

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    private RequestQueue requestQueue;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        // Setting up the view
        View view = inflater.inflate(R.layout.layout_news, container,false);

        // setting the title of the action bar
        getActivity().setTitle("What's New!");

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
        listView = view.findViewById(R.id.rv_news);
        //System.out.print("beforeOnResponse");

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        ParseJSON();

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
                    listView.clearTextFilter();
                } else {
                    adapter.filter(newText);
                }
                return true;
            }
        });

    }
    public void ParseJSON(){
        String url = "https://s3-ap-southeast-2.amazonaws.com/appcsea/news/newsList.json";
        Toast.makeText(getContext(), "Connection Established", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                        JSONArray jsonArray = response.getJSONArray("news");
                        for(int i=0; i< jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String catIcon = jsonObject.getString("icon");
                            String catName = jsonObject.getString("name");
                            String eventPic =  jsonObject.getString("image");
                            String eventHeading = jsonObject.getString("heading");
                            String eventDetail = jsonObject.getString("detail");
                            arrayList.add(new Model_News(catIcon, catName, eventPic, eventHeading, eventDetail));
                        }
                        adapter = new NewsAdapter(getActivity(), arrayList);
                        listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Connection failed", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

}
