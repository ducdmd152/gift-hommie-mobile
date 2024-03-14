package com.mobilers.gift_hommie_mobile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobilers.gift_hommie_mobile.CartActivity;
import com.mobilers.gift_hommie_mobile.R;

public class User_Page_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        Button btnCart = findViewById(R.id.btnCart);
        Button btnOrder = findViewById(R.id.btnOrder);
        Button btnChat = findViewById(R.id.btnChat);
        Button btnAddress = findViewById(R.id.btnAddress);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, CartActivity.class);
                startActivity(intent);
            }
        });

//        btnOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(User_Page_Activity.this, CheckoutActivity.class); ---> Check lại intent đúng chưa
//                startActivity(intent);
//            }
//        });


//        btnChat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(User_Page_Activity.this, ChatActivity.class); ----> Delete this when have Chat
//                startActivity(intent);
//            }
//        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, MapActivity.class);
                startActivity(intent);
            }
        });


    }
}


