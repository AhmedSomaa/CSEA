package com.sample.ahmed.csea.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import com.sample.ahmed.csea.MainHelpers.GMailSender;
import com.sample.ahmed.csea.R;

public class Activity_TA_Reservation extends AppCompatActivity {

    EditText studentName;
    Spinner TA_time, TA_course;
    CalendarView calendarView;
    Button submitButton;

    String reservedAt;
    String timeSpinner_value, courseSpinner_value;

    String TA_name, TA_mail, TA_officeHours, TA_phone;
    int TA_imageURL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ta_reservation);


        ActionBar actionBar = getSupportActionBar();

        //  Reservation views
        studentName = (EditText)findViewById(R.id.ta_res_name);
        TA_time = (Spinner)findViewById(R.id.ta_res_time);
        TA_course = (Spinner)findViewById(R.id.ta_res_course);
        calendarView = (CalendarView)findViewById(R.id.ta_res_day);

        //  Submission Button
        submitButton = (Button)findViewById(R.id.ta_res_submit);

        //  Setting the time slots of TA
        ArrayAdapter<String> Time_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ta_office_hours));
        Time_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TA_time.setAdapter(Time_adapter);


        //  Setting the courses the TA teaches
        ArrayAdapter<String> Course_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ta_course));
        Course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TA_course.setAdapter(Course_adapter);


        //  Receiving the TA info
        Bundle bundle = getIntent().getExtras();
        TA_officeHours = bundle.getString("TA_officHrs");
        TA_name = bundle.getString("TA_name");
        TA_mail = bundle.getString("TA_mail");
        String TA_rate = bundle.getString("TA_rate");
        TA_phone = bundle.getString("TA_phone");
        TA_imageURL = bundle.getInt("TA_imageURL");

        actionBar.setTitle(TA_name);


        //  Retrieving registeration data

        //  Retrieve the selected timeslot
        TA_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeSpinner_value = parent.getItemAtPosition(position).toString();
                //Toast.makeText(Activity_TA_Reservation.this, timeSpinner_value , Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //  Retrieve the selected course
        TA_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                courseSpinner_value = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //  Retrieving the date of appointment
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                //reservationDay = String.valueOf(day) + '-' + String.valueOf(month) + "-" + String.valueOf(year);
                reservedAt = day + "-" + month + "-" + year;
            }
        });

        //  Handling the submission button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(TA_mail, TA_name, studentName.getText().toString(), reservedAt, timeSpinner_value, courseSpinner_value);
            }
        });


    }


    // the menu to be displayed at Action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.ta_profile_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.ta_profile:
                Intent TA_profile_intent = new Intent(Activity_TA_Reservation.this, Activity_TA_Profile.class);
                TA_profile_intent.putExtra("TA_Pic", TA_imageURL);
                TA_profile_intent.putExtra("TA_Name", TA_name);
                TA_profile_intent.putExtra("TA_OfficeHours", TA_officeHours);
                TA_profile_intent.putExtra("TA_Email", TA_mail);
                TA_profile_intent.putExtra("TA_Phone", TA_phone);
                startActivity(TA_profile_intent);
                break;
        }
        return true;
    }

    //  send message
    private void sendMessage(final String ta_mail, final String ta_name, final String reservedBy, final String reservedAt, final String timeSpinner_value, final String courseSpinner_value) {
        final ProgressDialog dialog = new ProgressDialog(Activity_TA_Reservation.this);
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender("abokahfa", "Ahmed_96");
                    sender.sendMail("CSEA Clininc Appointment",  "Dear " + ta_name + ",\n\n\tPlease be informed that you have an appoinment with " + reservedBy
                            + " in the following day: " + reservedAt + " for the following time period: " + timeSpinner_value + " for assistance in the following course:  " + courseSpinner_value
                            + ".\n\nRegards,\nCSEA Team", "abokahfa", ta_mail);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }



}
