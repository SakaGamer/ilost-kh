package com.example.ilost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txGoSignIn;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.sign_up_btn_sign_up);
        txGoSignIn = findViewById(R.id.sign_up_txt_go_login);
        txGoSignIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int clickId = view.getId();
        if (clickId == R.id.sign_up_txt_go_login) {
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            finish();
        } else if (clickId == R.id.sign_up_btn_sign_up){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
}
