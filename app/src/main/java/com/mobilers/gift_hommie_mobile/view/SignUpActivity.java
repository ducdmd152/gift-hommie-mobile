package com.mobilers.gift_hommie_mobile.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.auth.Account;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.auth.AuthAPIService;
import com.mobilers.gift_hommie_mobile.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private EditText etUsername, etPassword, etName, etEmail, etPhone, etConfirmPassword;
    private Button btnLogin, btnSignUp;
    private GlobalService globalService;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmpassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        Context context = SignUpActivity.this;
        // Set click listener for login button
        btnSignUp.setOnClickListener(view -> {
            if(!checkInput())
                return;
            String name = etName.getText().toString().trim();
            String username = etUsername.getText().toString().trim().toLowerCase();
            String password = etPassword.getText().toString();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            Account register = new Account();
            register.setName(name);
            register.setFirstName(name);
            register.setUsername(username);
            register.setPassword(password);
            register.setRoleId(1);
            register.setEmail(email);
            register.setPhone(phone);
            AuthAPIService apiService = new AuthAPIService();
            apiService.register(register, new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        Account account = response.body();
                        GlobalService.getInstance().setAccount(register);
                        GlobalService.getInstance().setAuthenticated(true);
                        Intent intent = new Intent(context, ProductListActivity.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(context, "Đăng kí thất bại,\nvui lòng kiểm tra thông tin!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Set click listener for create account button
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError(REQUIRE);
            return  false;
        }

        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return  false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return  false;
        }

        if (!TextUtils.equals(etPassword.getText().toString(), etConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Password are not match!", Toast.LENGTH_LONG).show();
            return  false;
        }

        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(REQUIRE);
            return  false;
        }

        if (!Util.validateEmail(etEmail.getText().toString())) {
            Toast.makeText(this, "Invalid email format!", Toast.LENGTH_LONG).show();
            return  false;
        }

        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            etPhone.setError(REQUIRE);
            return  false;
        }

        if (!Util.validatePhoneNumber(etPhone.getText().toString())) {
            Toast.makeText(this, "Invalid phone format!", Toast.LENGTH_LONG).show();
            return  false;
        }

        return true;
    }
}

