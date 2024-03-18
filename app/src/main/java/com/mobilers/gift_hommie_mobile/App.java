package com.mobilers.gift_hommie_mobile;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.os.Build;

import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.UserAction;

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        PayPalCheckout.setConfig(new CheckoutConfig(
                this,
                "ARoi3O0eCaY4PgNsrZxTJklW9GbaWekKLptBbN6PXhZ4US6fIYkInRUJ65X93zScKp1pyZSCLLqDTZqx",
                Environment.SANDBOX,
                CurrencyCode.USD,
                UserAction.PAY_NOW,
                "com.mobilers.gifthommiemobile://paypalpay"
        ));
    }
    public static final String CHANNEL_ID_1 = "NOTIFICATION_1";

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            //notification
            CharSequence name = getString(R.string.notification);
            String description = getString(R.string.notification_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ID_1, name, importance);
            channel1.setDescription(description);



            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel1);
            }

        }
    }
}