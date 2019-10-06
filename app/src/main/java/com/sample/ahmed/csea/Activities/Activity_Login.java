package com.sample.ahmed.csea.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sample.ahmed.csea.R;

public class Activity_Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private RelativeLayout rellay1, rellay2;
    private Button signup, forgotPass, login;
    private Handler handler = new Handler();
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Activity_Login.this, Activity_Home.class));
            finish();
        }

        setContentView(R.layout.activity__login);

        // setting up the views
        inputEmail = (EditText) findViewById(R.id.input_mail);
        inputPassword = (EditText) findViewById(R.id.input_pass);
        signup = (Button) findViewById(R.id.btn_signup);
        login = (Button) findViewById(R.id.btn_login);
        forgotPass = (Button) findViewById(R.id.btn_forgetpass);
        rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout)findViewById(R.id.rellay2);
        progressBar = (ProgressBar)findViewById(R.id.login_progressbar);
        handler.postDelayed(runnable, 2000); // 2000 is the timeout for the splash


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        // Handling Signup Button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Login.this, Activity_Signup.class));
            }
        });

        // Forget Password Button
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Login.this, Activity_ForgetPassword.class));
            }
        });


        //Login Button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Activity_Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Activity_Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                } else {
                                    checkIfEmailVerified();
                                }
                            }});
                }
        });
    }

    // Check if the email is verified
    private void checkIfEmailVerified() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            // user is verified, so you can finish this activity or send user to activity which you want.
            Toast.makeText(Activity_Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Activity_Login.this, Activity_Home.class));
            finish();
        }
        else
        {
            // email is not verified, so just prompt the message to the user and restart this activity.
            // NOTE: don't forget to log out the user.
            Toast.makeText(Activity_Login.this, "Email Not Verified yet, check your email for verification or sign up", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            FirebaseAuth.getInstance().signOut();
        }
    }
}
