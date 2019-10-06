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
import com.sample.ahmed.csea.Adapters.CourseAdapter;
import com.sample.ahmed.csea.Adapters.ProfessorAdapter;
import com.sample.ahmed.csea.Models.Model_Course;
import com.sample.ahmed.csea.Models.Model_Professor;
import com.sample.ahmed.csea.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_Course_RecyclerView extends AppCompatActivity {

    ArrayList<Model_Course> mylist = new ArrayList<Model_Course>();
    CourseAdapter adapter;
    private RequestQueue requestQueue;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__course__recycler_view);

        Bundle bundle = getIntent().getExtras();

        //Action Bar Name
        ActionBar recyclerActionBar = getSupportActionBar();
        recyclerActionBar.setTitle(bundle.getString("KeyTitle"));

        recyclerView = findViewById(R.id.recycle_course);

        requestQueue = Volley.newRequestQueue(this);
        ParseJSON();
    }
    private void ParseJSON() {
        String url = "https://s3-ap-southeast-2.amazonaws.com/appcsea/courses/coursesList.json";
        //Toast.makeText(this, "Connection Established", Toast.LENGTH_SHORT).show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("courses");
                    for(int i=0; i< jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String code = jsonObject.getString("code");
                        String name = jsonObject.getString("name");
                        String credits =  jsonObject.getString("credits");
                        String prerequiste = jsonObject.getString("prerequiste");
                        String description = jsonObject.getString("description");
                        String when_offered = jsonObject.getString("when_offered");
                        String imgURL = jsonObject.getString("imgURL");
                        mylist.add(new Model_Course(code, name, prerequiste, description, when_offered, credits, imgURL));
                    }
                    adapter = new CourseAdapter(getApplicationContext(), mylist);
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
