package com.mobilers.gift_hommie_mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.service.GlobalService;

public class User_Page_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Button btnCart = findViewById(R.id.btnCart);
        Button btnOrder = findViewById(R.id.btnOrder);
        Button btnChat = findViewById(R.id.btnChat);
        Button btnAddress = findViewById(R.id.btnAddress);
        Button btnLogout = findViewById(R.id.btnLogout);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);

        tvName.setText(GlobalService.getInstance().getAccount().getFirstName() + GlobalService.getInstance().getAccount().getLastName());
        tvEmail.setText(GlobalService.getInstance().getAccount().getEmail());
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalService.getInstance().clear();
                Intent intent = new Intent(User_Page_Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, OrderActivity.class);
                startActivity(intent);
            }
        });


        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, TestChatLogin.class);
                intent.putExtra("mId", GlobalService.getInstance().getAccount().getUsername());
                startActivity(intent);
            }
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, MapActivity.class);
                startActivity(intent);
            }
        });


    }
}


