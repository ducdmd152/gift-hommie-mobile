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
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.auth.AuthAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUser, edtPwd;
    private Button btnLogin, btnSignup;
    private GlobalService globalService;
    private Context context;
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);
        edtUser = findViewById(R.id.edtUser);
        edtPwd = findViewById(R.id.edtPwd);
        // Set click listener for login button
        btnLogin.setOnClickListener(view ->{
            if(!checkInput())
                return;
            GlobalService.getInstance().setAccount(new Account(
                    edtUser.getText().toString().toLowerCase().trim(),
                    edtPwd.getText().toString()));
            AuthAPIService apiService = new AuthAPIService();
            apiService.login(new Callback<Account>() {
                @Override
                public void onResponse(Call<Account> call, Response<Account> response) {
                    if (response.isSuccessful()) {
                        Account account = response.body();
                        account.setPassword(edtPwd.getText().toString());
                        GlobalService.getInstance().setAccount(account);
                        GlobalService.getInstance().setAuthenticated(true);
                        Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(context, "Đăng nhập thất bại,\nvui lòng kiểm tra thông tin!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Account> call, Throwable t) {
                    Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                }
            });
        });
        // Set click listener for create account button
        btnSignup.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
    private boolean checkInput() {
        if (TextUtils.isEmpty(edtUser.getText().toString())) {
            edtUser.setError(REQUIRE);
            return  false;
        }

        if (TextUtils.isEmpty(edtPwd.getText().toString())) {
            edtPwd.setError(REQUIRE);
            return  false;
        }

        return true;
    }

}
