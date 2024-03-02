package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.product.ProductGET;
import com.mobilers.gift_hommie_mobile.service.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        APIService productGETAPIService = new APIService<ProductGET>("public/product", ProductGET.class);

        productGETAPIService.getAll(new Callback<List<ProductGET>>() {
            @Override
            public void onResponse(Call<List<ProductGET>> call, Response<List<ProductGET>> response) {
                if (response.isSuccessful()) {
                    List<ProductGET> products = response.body();
                    if (products != null) {
                        for (ProductGET product : products) {
                            Log.d(" : " + product.getId() , product.getName());
                        }
                    }
                    Toast.makeText(MainActivity.this, "Loaded " + products.size() + " items successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProductGET>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}