package com.mobilers.gift_hommie_mobile;
import android.app.Application;

import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.UserAction;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PayPalCheckout.setConfig(new CheckoutConfig(
                this,
                "ARoi3O0eCaY4PgNsrZxTJklW9GbaWekKLptBbN6PXhZ4US6fIYkInRUJ65X93zScKp1pyZSCLLqDTZqx",
                Environment.SANDBOX,
                CurrencyCode.USD,
                UserAction.PAY_NOW,
                "com.mobilers.gifthommiemobile://paypalpay"
        ));
    }
}