package com.sample.ahmed.csea.Activities;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sample.ahmed.csea.R;
import com.sample.ahmed.csea.Fragments.assocnFragment;
import com.sample.ahmed.csea.Fragments.clinicFragment;
import com.sample.ahmed.csea.Fragments.deptFragment;
import com.sample.ahmed.csea.Fragments.internsFragment;
import com.sample.ahmed.csea.Fragments.newsFragment;


public class Activity_Home extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    selectedFragment = new newsFragment();
                    break;
                case R.id.navigation_clinic:
                    selectedFragment = new clinicFragment();
                   break;
                case R.id.navigation_assocn:
                    selectedFragment = new assocnFragment();
                    break;
                case R.id.navigation_interns:
                    selectedFragment = new internsFragment();
                    break;
                case R.id.navigation_dept:
                    selectedFragment = new deptFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);
        //Toolbar toolbar = findViewById(R.id.home_tb);
        //setActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        //actionBar.setTitle("Home");

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new newsFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*getMenuInflater().inflate(R.menu.search_model_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.news_search);
        //returns the object of the class that's specified within "actionViewClass" field
        SearchView searchView = (SearchView) searchItem.getActionView();*/
        return true;
    }
}
