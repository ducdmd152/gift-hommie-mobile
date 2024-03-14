package com.mobilers.gift_hommie_mobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mobilers.gift_hommie_mobile.adapter.CartListAdapter;
import com.mobilers.gift_hommie_mobile.model.cart.CartDTO;
import com.mobilers.gift_hommie_mobile.model.cart.CartListResponseDTO;
import com.mobilers.gift_hommie_mobile.model.checkout.CheckoutDTO;
import com.mobilers.gift_hommie_mobile.model.product.Product;
import com.mobilers.gift_hommie_mobile.service.GlobalService;
import com.mobilers.gift_hommie_mobile.service.cart.CartAPIService;
import com.mobilers.gift_hommie_mobile.view.CheckoutActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private Context context;
    private GlobalService globalService;
    private CheckoutDTO checkoutDTO;
    private CartListAdapter cartListAdapter;
    private RecyclerView rvItemCart;
    private List<CartDTO> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = CartActivity.this;

        initService();
        binding();
        initData();

        list = new ArrayList<>();
        cartListAdapter = new CartListAdapter(context, list);
        cartListAdapter.setOnItemClickListener(v -> {

        });

        rvItemCart.setAdapter(cartListAdapter);
        rvItemCart.setLayoutManager(new LinearLayoutManager(this));

        CartAPIService cartAPIService = new CartAPIService();
        cartAPIService.getAll(new Callback<CartListResponseDTO>() {
            @Override
            public void onResponse(Call<CartListResponseDTO> call, Response<CartListResponseDTO> response) {
                if (response.isSuccessful()) {
                    List<CartDTO> result = response.body().getContent();
                    list.clear();
                    if (result != null) list.addAll(result);
                    cartListAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "Không thể tải danh sách sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartListResponseDTO> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Đã xảy ra lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        cartListAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                //updateCheckoutSummary();
            }
        });
        cartListAdapter.setOnItemClickListener(new CartListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CartDTO item = list.get(position);
                Log.d("Index", "" + position);
//                View currentView = rvItemCart.getChildAt(position);
                if (item == null) return;
                if (globalService.getCheckoutDTO().getCarts().contains(item)) {
                    globalService.getCheckoutDTO().getCarts().remove(item);
//                    currentView.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    globalService.getCheckoutDTO().getCarts().add(item);
//                    currentView.setBackgroundColor(Color.CYAN);
                }
                cartListAdapter.notifyItemChanged(position);
            }
        });
        //events();
    }

    private void initService() {
        globalService = GlobalService.getInstance();
        checkoutDTO = new CheckoutDTO();
        checkoutDTO.setCarts(new ArrayList<>());
        globalService.setCheckoutDTO(checkoutDTO);
    }

    private void initData() {
    }

    private void binding() {
        rvItemCart = findViewById(R.id.rvItemCart);
    }
}