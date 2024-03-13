package com.mobilers.gift_hommie_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.mobilers.gift_hommie_mobile.adapter.CartListAdapter;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.view.CheckoutActivity;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private Context context;
    private GlobalService globalService;
    private RecyclerView rvItemCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = CartActivity.this;

        initService();
        binding();
        initData();
        //events();
    }

    private void initService() {
        globalService = GlobalService.getInstance();
        //checkoutDTO = new CheckoutDTO();
        //globalService.setCheckoutDTO(checkoutDTO);
    }
    private void binding() {
        rvItemCart = findViewById(R.id.rvItemCart);
    }
    private void initData() {
        List<CartDTO> carts = new ArrayList<>();
        carts.add(new CartDTO(127, 62, 1, new Product(62, "Cốc Sứ Họa Tiết", "", 333, 150000,"https://anh.quatructuyen.com/media/catalog/product/cache/1/image/480x480/9df78eab33525d08d6e5fb8d27136e95/c/_/c_c_s_h_a_ti_t_beautiful_girl_7.jpg", 1)));
        carts.add(new CartDTO(127, 62, 1, new Product(62, "Cốc Sứ Họa Tiết", "", 333, 150000,"https://anh.quatructuyen.com/media/catalog/product/cache/1/image/480x480/9df78eab33525d08d6e5fb8d27136e95/c/_/c_c_s_h_a_ti_t_beautiful_girl_7.jpg", 1)));

        CartListAdapter cartListAdapter = new CartListAdapter(context, carts);
        rvItemCart.setAdapter(cartListAdapter);
        rvItemCart.setLayoutManager (new LinearLayoutManager(this));
        //updateCheckoutSummary();
        cartListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                //updateCheckoutSummary();
            }
        });
    }
}