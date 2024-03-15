package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.mobilers.gift_hommie_mobile.R;

public class CompetePaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compete_payment);

        LinearLayout btnBackToHome = findViewById(R.id.btnBackToHome);
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(CompetePaymentActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
    }
}