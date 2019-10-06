package com.sample.ahmed.csea.Fragments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sample.ahmed.csea.Activities.Activity_Login;
import com.sample.ahmed.csea.Activities.Activity_Professor_RecyclerView;
import com.sample.ahmed.csea.R;


public class assocnFragment extends Fragment {

    private CardView cardView_1, cardView_2;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.layout_assocn, container,false);
        getActivity().setTitle("Association");

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

        cardView_1 = view.findViewById(R.id.cv_1);
        cardView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Meet our beloved board!", Toast.LENGTH_SHORT).show();
                /*Intent new_i = new Intent(getContext(), Activity_Professor_RecyclerView.class);
                new_i.putExtra("KeyTitle", "Meeth the Board!");
                startActivity(new_i);*/
            }
        });
        cardView_2 = view.findViewById(R.id.cv_2);
        cardView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Keep up with our outings plans!", Toast.LENGTH_SHORT).show();
                /*Intent new_i = new Intent(getContext(), Activity_Professor_RecyclerView.class);
                new_i.putExtra("KeyTitle", "Social Lab!");
                startActivity(new_i);*/
            }
        });
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
