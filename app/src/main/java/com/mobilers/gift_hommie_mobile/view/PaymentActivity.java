package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.mobilers.gift_hommie_mobile.R;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingInfoDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.ShippingResponseDTO;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.checkout.CheckoutAPIService;
import com.mobilers.gift_hommie_mobile.service.ghn.GHNService;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.OnCaptureComplete;
import com.paypal.checkout.order.OrderRequest;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PaymentButtonContainer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    private static final String TAG = "PAYMENT: ";
    PaymentButtonContainer btnPaypal;
    TextView btnCOD;
    double rate = 0.000042;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        CheckoutDTO checkoutDTO = GlobalService.getInstance().getCheckoutDTO();
        btnCOD = findViewById(R.id.btnCOD);
        btnPaypal = findViewById(R.id.btnPaypal);
        String amount = String.format(Locale.US, "%.2f", (checkoutDTO.getTotal()*rate));
        Log.i("Payment Amount: ", checkoutDTO.getTotal() + " VND -> $" + amount);

        CheckoutAPIService apiService = new CheckoutAPIService();
        Context context = PaymentActivity.this;
        btnCOD.setOnClickListener(v -> {
            checkoutDTO.setPaymentMethod(1);
            apiService.checkout(checkoutDTO, new Callback<CheckoutDTO>() {
                @Override
                public void onResponse(Call<CheckoutDTO> call, Response<CheckoutDTO> response) {
                    if (response.isSuccessful()) {
                        GHNService.createOrder(checkoutDTO, new Callback<ShippingResponseDTO>() {
                            @Override
                            public void onResponse(Call<ShippingResponseDTO> call, Response<ShippingResponseDTO> response) {
                                if (response.isSuccessful()) {
                                }
                                else {
                                }
                            }

                            @Override
                            public void onFailure(Call<ShippingResponseDTO> call, Throwable t) {
                            }
                        });
                        Intent intent = new Intent(PaymentActivity.this, CompetePaymentActivity.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(context, "Checkout thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<CheckoutDTO> call, Throwable t) {
                    Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        btnPaypal.setup(
                new CreateOrder() {
                    @Override
                    public void create(@NotNull CreateOrderActions createOrderActions) {
                        Log.d(TAG, "create: ");
                        ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<>();
                        purchaseUnits.add(
                                new PurchaseUnit.Builder()
                                        .amount(
                                                new Amount.Builder()
                                                        .currencyCode(CurrencyCode.USD)
                                                        .value(amount)
                                                        .build()
                                        )
                                        .build()
                        );
                        OrderRequest order = new OrderRequest(
                                OrderIntent.CAPTURE,
                                new AppContext.Builder()
                                        .userAction(UserAction.PAY_NOW)
                                        .build(),
                                purchaseUnits
                        );
                        createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                    }
                },
                new OnApprove() {
                    @Override
                    public void onApprove(@NotNull Approval approval) {


                        approval.getOrderActions().capture(new OnCaptureComplete() {
                            @Override
                            public void onCaptureComplete(@NotNull CaptureOrderResult result) {
                                Log.d(TAG, String.format("CaptureOrderResult: %s", result));
                                checkoutDTO.setPaymentMethod(2);
                                apiService.checkout(checkoutDTO, new Callback<CheckoutDTO>() {
                                    @Override
                                    public void onResponse(Call<CheckoutDTO> call, Response<CheckoutDTO> response) {
                                        if (response.isSuccessful()) {
                                            GHNService.createOrder(checkoutDTO, new Callback<ShippingResponseDTO>() {
                                                @Override
                                                public void onResponse(Call<ShippingResponseDTO> call, Response<ShippingResponseDTO> response) {
                                                    if (response.isSuccessful()) {
                                                    }
                                                    else {
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ShippingResponseDTO> call, Throwable t) {
                                                }
                                            });
                                            Intent intent = new Intent(PaymentActivity.this, CompetePaymentActivity.class);
                                            startActivity(intent);
                                        }

                                        else {
                                            Toast.makeText(context, "Checkout thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<CheckoutDTO> call, Throwable t) {
                                        Toast.makeText(context, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Toast.makeText(PaymentActivity.this, "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
        );
    }
}