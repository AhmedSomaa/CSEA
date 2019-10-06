package com.sample.ahmed.csea.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sample.ahmed.csea.Adapters.ProfessorAdapter;
import com.sample.ahmed.csea.Models.Model;
import com.sample.ahmed.csea.Models.Model_Professor;
import com.sample.ahmed.csea.R;
import com.sample.ahmed.csea.Adapters.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Professor_RecyclerView extends AppCompatActivity {

    ArrayList<Model_Professor> mylist = new ArrayList<Model_Professor>();
    ProfessorAdapter adapter;
    private RequestQueue requestQueue;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__recycler_view);

        Bundle bundle = getIntent().getExtras();

        //Action Bar Name
        ActionBar recyclerActionBar = getSupportActionBar();
        recyclerActionBar.setTitle(bundle.getString("KeyTitle"));



        recyclerView = findViewById(R.id.recycle1);

        requestQueue = Volley.newRequestQueue(this);
        ParseJSON();
    }

    private void ParseJSON() {
        String url = "https://s3-ap-southeast-2.amazonaws.com/appcsea/professors/professorsList.json";
        Toast.makeText(this, "Connection Established", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                        JSONArray jsonArray = response.getJSONArray("professors");
                        for(int i=0; i< jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String position = jsonObject.getString("position");
                            String email =  jsonObject.getString("email");
                            String office = jsonObject.getString("office");
                            String bio = jsonObject.getString("bio");
                            String research = jsonObject.getString("research");
                            String publications = jsonObject.getString("publications");
                            String courses = jsonObject.getString("courses");
                            String imageURL = jsonObject.getString("imageURL");
                            mylist.add(new Model_Professor(name, position, email, office, bio, research, publications, courses, imageURL));
                    }
                    adapter = new ProfessorAdapter(getApplicationContext(), mylist);
                    recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Connection failed", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }
}
