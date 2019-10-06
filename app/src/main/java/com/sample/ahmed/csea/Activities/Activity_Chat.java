package com.sample.ahmed.csea.Activities;



import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.sample.ahmed.csea.Models.ChatMessage;
import com.sample.ahmed.csea.Models.Model_News;
import com.sample.ahmed.csea.R;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;


import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Activity_Chat extends AppCompatActivity {


    ListView listOfMessage;
    private FirebaseListAdapter<ChatMessage> adapter;

    private FirebaseListAdapter<ChatMessage> adapterSearch;


    RelativeLayout activity_main;

    //Add Emojicon
    EmojiconEditText emojiconEditText;
    ImageView emojiButton,submitButton;
    EmojIconActions emojIconActions;

    String Ccode_retrieve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__chat);
        Bundle bundle = getIntent().getExtras();
         Ccode_retrieve = bundle.getString("courseCode");

        activity_main = (RelativeLayout)findViewById(R.id.activity__chat);

        //Add Emoji
        emojiButton = (ImageView)findViewById(R.id.emoji_button);
        submitButton = (ImageView)findViewById(R.id.submit_button);
        emojiconEditText = (EmojiconEditText)findViewById(R.id.emojicon_edit_text);
        emojIconActions = new EmojIconActions(getApplicationContext(),activity_main,emojiButton,emojiconEditText);
        emojIconActions.ShowEmojicon();

        final String username =  FirebaseAuth.getInstance().getCurrentUser().getEmail().substring(0, FirebaseAuth.getInstance().getCurrentUser().getEmail().length() - 13 );

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chat");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference(Ccode_retrieve).push().setValue(new ChatMessage(emojiconEditText.getText().toString(),
                       username));

                emojiconEditText.setText("");
                emojiconEditText.requestFocus();
            }
        });


        displayChatMessage();


    }

    private void displayChatMessage() {

        listOfMessage = (ListView)findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference(Ccode_retrieve))
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                //Get references to the views of list_item.xml
                TextView messageText, messageUser, messageTime;
                messageText = (EmojiconTextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

            }
        };
        listOfMessage.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.chat_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.chat_search:
                search(item);
                break;
        }
        return true;
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
                   displayChatMessage();
                } else {
                    filter(newText);
                }
                return true;
            }
        });

    }

    //filter by heading and subheading
    public void filter(String charText){
      //  Toast.makeText(Activity_Chat.this, "Started Search", Toast.LENGTH_LONG).show();
        DatabaseReference msgsfiltered;
        msgsfiltered =  FirebaseDatabase.getInstance().getReference();

       // Query firebaseSearchQuery = msgsfiltered.orderByChild("messageText").startAt(charText).endAt(charText + "\uf8ff");
       // Query firebaseSearchQuery = msgsfiltered.child(Ccode_retrieve).orderByChild("messageText").equalTo(charText);
       Query firebaseSearchQuery = msgsfiltered.child(Ccode_retrieve).orderByChild("messageText").startAt(charText).endAt(charText + "\uf8ff");



        adapterSearch = new FirebaseListAdapter<ChatMessage>(this,ChatMessage.class,R.layout.list_item, firebaseSearchQuery)
        {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {

                //Get references to the views of list_item.xml
                TextView messageText, messageUser, messageTime;
                messageText = (EmojiconTextView) v.findViewById(R.id.message_text);
                messageUser = (TextView) v.findViewById(R.id.message_user);
                messageTime = (TextView) v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", model.getMessageTime()));

            }
        };
        listOfMessage.setAdapter(adapterSearch);
    }



    }


