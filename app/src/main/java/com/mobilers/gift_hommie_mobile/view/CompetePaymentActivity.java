package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.App;
import com.mobilers.gift_hommie_mobile.R;

import java.util.Date;

public class CompetePaymentActivity extends AppCompatActivity {
    private static final String TITLE_PUSH = "Đã đặt hàng";
    private static final String CONTENT_PUSH = "Đon hàng sẽ sóm được giao đến bạn";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compete_payment);

        sendPaymentSuccessNotify();

        LinearLayout btnBackToHome = findViewById(R.id.btnBackToHome);
        btnBackToHome.setOnClickListener(v -> {
            Intent intent = new Intent(CompetePaymentActivity.this, ProductListActivity.class);
            startActivity(intent);
        });
    }

    private void sendPaymentSuccessNotify() {
        Notification notification = new NotificationCompat.Builder(this, App.CHANNEL_ID)
                .setContentTitle("Title push notification")
                .setContentText("Message push notification")
                .setSmallIcon(R.drawable.favicon)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(getNotificationId(), notification);
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}