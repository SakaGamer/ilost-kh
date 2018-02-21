package com.example.ilost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txGoSignUp;
    Button btnSignIn;

    private boolean isFirstStart;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignIn = findViewById(R.id.sign_in_btn_sign_in);
        txGoSignUp = findViewById(R.id.sign_in_txt_don_have_acc);
        txGoSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        appIntro();
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
    }


    @Override
    public void onClick(View view) {
        int clickId = view.getId();
        if (clickId == R.id.sign_in_txt_don_have_acc) {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            finish();
        } else if (clickId == R.id.sign_in_btn_sign_in){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }


    private void appIntro() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //  Intro App Initialize SharedPreferences
                SharedPreferences getSharedPreferences = PreferenceManager
                        .getDefaultSharedPreferences(getBaseContext());
                //  Create a new boolean and preference and set it to true
                isFirstStart = getSharedPreferences.getBoolean("firstStart", true);

                //  Check either activity or app is open very first time or not and do action
                if (isFirstStart) {
                    //  Launch application introduction screen
                    Intent i = new Intent(getApplicationContext(), MyIntro.class);
                    startActivity(i);
                    SharedPreferences.Editor e = getSharedPreferences.edit();
                    e.putBoolean("firstStart", false);
                    e.apply();
                }
            }
        });
        t.start();
    }

}
