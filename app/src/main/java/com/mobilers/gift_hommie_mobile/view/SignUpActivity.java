package com.mobilers.gift_hommie_mobile.view;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.service.GlobalService;

public class SignUpActivity extends AppCompatActivity {
    public class LoginActivity extends AppCompatActivity {
        private EditText edtUser, edtPwd;
        private Button btnLogin, btnSignup;
        private GlobalService globalService;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);


            // Set click listener for login button
            btnLogin.setOnClickListener(view -> {
                Intent intent = new Intent(SignUpActivity.this, ProductListActivity.class);
                startActivity(intent);
            });

            // Set click listener for create account button
            btnSignup.setOnClickListener(view -> {
                Intent intent = new Intent(SignUpActivity.this, SignUpActivity.class);
                startActivity(intent);
            });
        }
    }

}

