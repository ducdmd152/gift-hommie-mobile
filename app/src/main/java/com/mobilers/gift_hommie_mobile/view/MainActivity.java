package com.mobilers.gift_hommie_mobile.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.R;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.model.product.ProductListResponseDTO;
import com.mobilers.gift_hommie_mobile.service.APIClient;
import com.mobilers.gift_hommie_mobile.service.APIService;
import com.mobilers.gift_hommie_mobile.service.product.ProductAPIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProductAPIService productAPIService = new ProductAPIService();

        productAPIService.getAll(new Callback<ProductListResponseDTO>() {
            @Override
            public void onResponse(Call<ProductListResponseDTO> call, Response<ProductListResponseDTO> response) {
                if (response.isSuccessful()) {
                    ProductListResponseDTO body = response.body();
                    if (body != null && body.getContent() != null) {
                        for (Product product : body.getContent()) {
                            Log.d(" Product: " + product.getId() , product.getName());
                        }
                    }
                    Toast.makeText(MainActivity.this, "Loaded items successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponseDTO> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Cannot connect to server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}