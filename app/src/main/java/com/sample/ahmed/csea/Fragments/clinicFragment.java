package com.sample.ahmed.csea.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sample.ahmed.csea.Activities.Activity_Login;
import com.sample.ahmed.csea.R;
import com.sample.ahmed.csea.Adapters.viewPagerAdapter;

public class clinicFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        // Setting up the view
        View view = inflater.inflate(R.layout.layout_clinic, container,false);

        // setting the title of the action bar
        getActivity().setTitle("CSEA Clinic");

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

        tabLayout = view.findViewById(R.id.tablayout_1);
        viewPager = view.findViewById(R.id.viewpager_1);

        viewPagerAdapter adapter = new viewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new taFragment(), "TA");
        adapter.addFragment(new profFragment(), "Professor");
        adapter.addFragment(new advisorFragment(), "Advising");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    // the menu to be displayed at Action bar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_model_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
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


}
